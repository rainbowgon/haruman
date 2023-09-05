package ssafy.haruman.domain.profile.dto.response;

import lombok.*;
import ssafy.haruman.domain.profile.entity.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SingleProfileResponseDto {

    private String nickname;

    public static SingleProfileResponseDto from(Profile profile) {
        return SingleProfileResponseDto.builder()
                .nickname(profile.getNickname())
                .build();
    }
}
