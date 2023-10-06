package ssafy.haruman.domain.profile.dto.response;

import lombok.*;
import ssafy.haruman.domain.profile.entity.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SingleProfileResponseDto {

    private Long profileId;
    private String nickname;
    private String profileImage;

    public static SingleProfileResponseDto from(Profile profile, String profileImageUrl) {
        return SingleProfileResponseDto.builder()
                .profileId(profile.getId())
                .nickname(profile.getNickname())
                .profileImage(profileImageUrl)
                .build();
    }

}
