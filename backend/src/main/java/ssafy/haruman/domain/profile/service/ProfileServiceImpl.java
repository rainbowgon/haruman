package ssafy.haruman.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
import ssafy.haruman.domain.profile.dto.request.ProfileUpdateRequestDto;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.domain.profile.repository.ProfileRepository;
import ssafy.haruman.global.error.exception.ProfileNotFoundException;
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
        profileRepository.save(profile);
        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage()));
    }

    @Override
    public SingleProfileResponseDto updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        Profile profile = this.findOneProfileById(profileUpdateRequestDto.getProfileId());
        profile.updateProfile(profileUpdateRequestDto.getNickname());
        profileRepository.save(profile);
        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage()));
    }

    @Override
    public SingleProfileResponseDto uploadNewProfileImage(Long profileId, MultipartFile multipartFile) {
        Profile profile = this.findOneProfileById(profileId);
        s3FileService.deleteImage(profile.getProfileImage());

        if (!multipartFile.isEmpty()) {
            profile.uploadNewProfileImage();
        }
    }

    @Override
    public SingleProfileResponseDto selectOneProfile(Long profileId) {
        Profile profile = this.findOneProfileById(profileId);
        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage())); // TODO S3에서 이미지 찾아서 URL 반환
    }

    @Override
    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }

    private Profile findOneProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> ProfileNotFoundException.EXCEPTION);
    }
}
