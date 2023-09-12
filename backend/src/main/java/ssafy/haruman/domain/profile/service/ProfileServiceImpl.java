package ssafy.haruman.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
import ssafy.haruman.domain.profile.dto.request.ProfileUpdateRequestDto;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.domain.profile.repository.ProfileRepository;
import ssafy.haruman.global.service.S3FileService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final S3FileService s3FileService;

    private final String S3_PATH = "image/profile/";

    @Override
    public SingleProfileResponseDto createProfile(ProfileCreateRequestDto profileCreateRequestDto, MultipartFile multipartFile) throws IOException {
        Profile profile = profileCreateRequestDto.toEntity();

        String savedFilename = s3FileService.saveFile(S3_PATH, multipartFile);
        profile.uploadNewProfileImage(S3_PATH, savedFilename);

        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage()));
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
