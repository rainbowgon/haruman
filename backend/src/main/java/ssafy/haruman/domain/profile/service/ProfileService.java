package ssafy.haruman.domain.profile.service;

import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

import java.io.IOException;

public interface ProfileService {

    SingleProfileResponseDto createProfile(Member member, String nickname, MultipartFile multipartFile)
            throws IOException;

    SingleProfileResponseDto updateProfile(Profile profile, String nickname, MultipartFile profileImage)
            throws IOException;

    SingleProfileResponseDto selectOneProfile(Profile profile);

    SingleProfileResponseDto selectOneProfileById(Long profileId);

    void deleteProfile(Long profileId);

    void saveProfileFromOAuth(Member member, String nickname, String oauthProfileImage);
}
