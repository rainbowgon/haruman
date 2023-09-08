package ssafy.haruman.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
import ssafy.haruman.domain.profile.dto.request.ProfileUpdateRequestDto;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.domain.profile.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public SingleProfileResponseDto createProfile(ProfileCreateRequestDto profileCreateRequestDto) {
        return null;
    }

    @Override
    public SingleProfileResponseDto updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        return null;
    }

    @Override
    public SingleProfileResponseDto selectOneProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("없음"));
        return SingleProfileResponseDto.from(profile);
    }

    @Override
    public void deleteProfile(Long profileId) {

    }
}
