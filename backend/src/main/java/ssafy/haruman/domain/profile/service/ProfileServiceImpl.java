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
        Profile profile = profileCreateRequestDto.toEntity();
        // TODO S3에 이미지 저장 및 URL 반환
        // TODO ProfileImage 개체를 만들고 Profile에 넣어야 합니다.
        return SingleProfileResponseDto.from(profile, null);
    }

    @Override
    public SingleProfileResponseDto updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        // TODO 프로필 수정 구현 필요
        return null;
    }

    @Override
    public SingleProfileResponseDto selectOneProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RuntimeException("없음"));
        return SingleProfileResponseDto.from(profile, null); // TODO S3에서 이미지 찾아서 URL 반환
    }

    @Override
    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }
}
