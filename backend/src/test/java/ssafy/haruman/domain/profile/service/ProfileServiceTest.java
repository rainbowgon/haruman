package ssafy.haruman.domain.profile.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.domain.profile.repository.ProfileRepository;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@DisplayName("ProfileService 테스트")
class ProfileServiceTest {

    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @BeforeEach
    void beforeEach() {
        profileService = new ProfileServiceImpl(profileRepository);
    }

    @Test
    @DisplayName("레포지토리에서 하나의 프로필 조회")
    void getOneProfileTest() throws Exception {
        //given
        Profile sampleProfile = Profile.builder().id(1L).nickname("sample!!").build();
        given(profileRepository.findById(1L)).willReturn(Optional.of(sampleProfile));

        //when
        SingleProfileResponseDto profileResponseDto = profileService.selectOneProfile(1L);

        //then
        Assertions.assertThat(profileResponseDto.getNickname()).isEqualTo(sampleProfile.getNickname());
    }
}