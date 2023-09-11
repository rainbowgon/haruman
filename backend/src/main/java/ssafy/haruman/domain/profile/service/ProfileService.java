package ssafy.haruman.domain.profile.service;

import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
import ssafy.haruman.domain.profile.dto.request.ProfileUpdateRequestDto;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;

public interface ProfileService {

    SingleProfileResponseDto createProfile(ProfileCreateRequestDto profileCreateRequestDto);

    SingleProfileResponseDto updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    SingleProfileResponseDto selectOneProfile(Long profileId);

    void deleteProfile(Long profileId);
}
