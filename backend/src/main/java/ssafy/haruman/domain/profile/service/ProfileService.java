package ssafy.haruman.domain.profile.service;

import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;

import java.io.IOException;

public interface ProfileService {

    SingleProfileResponseDto createProfile(Member member, String nickname, MultipartFile multipartFile) throws IOException;

    SingleProfileResponseDto updateProfile(Long profileId, String nickname, MultipartFile profileImage) throws IOException;

    SingleProfileResponseDto selectOneProfile(Long profileId);

    void deleteProfile(Long profileId);

    void saveProfileFromOAuth(Member member, String nickname, String oauthProfileImage) throws IOException;
}
