package ssafy.haruman.domain.challenge.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeUserInfoDto {

    private Long profileId;
    private Integer usedAmount;
    private LocalDateTime challengeStartTime;
    private LocalDateTime latestExpensePayTime;

    public static ChallengeUserInfoDto from(ChallengeUserInfoMapping info) {
        return ChallengeUserInfoDto.builder()
                .profileId(info.getProfileId())
                .usedAmount(info.getUsedAmount())
                .challengeStartTime(info.getStartTime())
                .latestExpensePayTime(info.getCreatedAt())
                .build();
    }

}
