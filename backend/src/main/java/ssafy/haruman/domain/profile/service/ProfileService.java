package ssafy.haruman.domain.profile.service;

import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;

public interface ProfileService {

    SingleProfileResponseDto getOneProfile(Long profileId);
}
