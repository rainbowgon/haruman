package ssafy.haruman.domain.profile.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ssafy.haruman.domain.profile.repository.ProfileRepository;
import ssafy.haruman.global.error.exception.ProfileNotFoundException;
import ssafy.haruman.global.service.S3FileService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("프로필 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private S3FileService s3FileService;

    @InjectMocks
    private ProfileServiceImpl profileService;


    @BeforeEach
    void beforeEach() {
    }

    @Test
    @DisplayName("존재하지 않은 ID의 프로필에 접근하면 ProfileNotFoundException이 발생한다.")
    public void noProfileException() throws Exception {
        //given
        BDDMockito.given(profileRepository.findById(any())).willReturn(Optional.empty());

        //when

        //then
        assertThrows(ProfileNotFoundException.class, () ->
                profileService.selectOneProfile(1L)
        );
    }

}