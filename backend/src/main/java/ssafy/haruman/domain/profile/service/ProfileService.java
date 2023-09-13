package ssafy.haruman.domain.profile.service;

import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
import ssafy.haruman.domain.profile.dto.request.ProfileUpdateRequestDto;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;

import java.io.IOException;

public interface ProfileService {

    SingleProfileResponseDto createProfile(ProfileCreateRequestDto profileCreateRequestDto, MultipartFile multipartFile) throws IOException;

    SingleProfileResponseDto updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    SingleProfileResponseDto uploadNewProfileImage(Long profileId, MultipartFile multipartFile);

    SingleProfileResponseDto selectOneProfile(Long profileId);

    void deleteProfile(Long profileId);
}
