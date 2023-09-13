package ssafy.haruman.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
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
    public SingleProfileResponseDto createProfile(String nickname, MultipartFile multipartFile) throws IOException {

        // TODO 멤버와의 매핑
        Profile profile = Profile.builder()
//                .member()
                .nickname(nickname)
                .build();

        String savedFilename = s3FileService.saveFile(S3_PATH, multipartFile);
        profile.uploadNewProfileImage(S3_PATH, savedFilename);
        profileRepository.save(profile);
        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage()));
    }

    @Override
    @Transactional
    public SingleProfileResponseDto updateProfile(Long profileId, String nickname, MultipartFile profileImage) throws IOException {
        Profile profile = this.findOneProfileById(profileId);
        profile.updateProfile(nickname);
        this.uploadNewProfileImage(profile, profileImage);
        return SingleProfileResponseDto.from(profile, s3FileService.getS3Url(profile.getProfileImage()));
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

    private void uploadNewProfileImage(Profile profile, MultipartFile multipartFile) throws IOException {
        this.deleteExistingProfileImage(profile);
        if (!multipartFile.isEmpty()) {
            String savedFilename = s3FileService.saveFile(S3_PATH, multipartFile);
            profile.uploadNewProfileImage(S3_PATH, savedFilename);
        }
    }

    private void deleteExistingProfileImage(Profile profile) {
        s3FileService.deleteImage(profile.getProfileImage());
        profile.deleteProfileImage();
    }
}
