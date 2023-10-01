package ssafy.haruman.domain.profile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.domain.profile.entity.ProfileImage;
import ssafy.haruman.domain.profile.repository.ProfileRepository;
import ssafy.haruman.global.error.exception.ProfileNotFoundException;
import ssafy.haruman.global.service.S3FileService;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@DisplayName("프로필 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Mock
    private S3FileService s3FileService;

    private ProfileServiceImpl profileService;

    private final String S3_PATH = "image/profile/";

    @BeforeEach
    void beforeEach() {
        profileService = new ProfileServiceImpl(profileRepository, s3FileService);
    }

    @Test
    @DisplayName("존재하지 않은 ID의 프로필에 접근하면 ProfileNotFoundException이 발생한다.")
    public void noProfileException() throws Exception {
        //given

        //when

        //then
        assertThrows(ProfileNotFoundException.class, () ->
                profileService.selectOneProfile(1L)
        );
    }

    @Test
    @DisplayName("프로필을 저장하면 연결된 이미지 파일도 같이 저장된다.")
    void saveProfileImage() throws Exception {
        //given
        final String TEST_PROFILE_IMAGE = "TEST_PROFILE_IMAGE";

        Profile profile = Profile.builder().
                nickname("TEST_PROFILE").build();
        profile.uploadNewProfileImage(S3_PATH, TEST_PROFILE_IMAGE);

        //when
        profileRepository.save(profile);
        entityManager.clear();

        //then
        Profile foundProfile = profileRepository.findById(profile.getId()).orElseThrow();
        ProfileImage foundProfileImage = foundProfile.getProfileImage();
        assertThat(foundProfileImage.getSavedFilename()).isEqualTo(TEST_PROFILE_IMAGE);
    }

}