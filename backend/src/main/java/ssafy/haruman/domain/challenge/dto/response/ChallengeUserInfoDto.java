package ssafy.haruman.domain.challenge.dto.response;

import lombok.*;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeUserInfoDto {

    private String nickname;
    private String profileImage;
    private Integer usedAmount;
    private LocalDateTime challengeStartTime;
    private LocalDateTime latestExpensePayTime;

    public static ChallengeUserInfoDto from(ChallengeUserInfoMapping info, String profileImageUrl) {
        return ChallengeUserInfoDto.builder()
                .nickname(info.getNickname())
                .profileImage(profileImageUrl)
                .usedAmount(info.getUsedAmount())
                .challengeStartTime(info.getStartTime())
                .latestExpensePayTime(info.getCreatedAt())
                .build();
    }

}
