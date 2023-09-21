package ssafy.haruman.domain.challenge.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;
import ssafy.haruman.domain.challenge.entity.ViewStatus;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeResponseDto {

    private Long id;
    private String nickname;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ChallengeStatus challengeStatus;
    private Integer targetAmount;
    private Integer usedAmount;
    private Integer leftoverAmount;
    private ViewStatus isViewed;

    public static ChallengeResponseDto from(Challenge challenge) {
        return ChallengeResponseDto.builder()
                .id(challenge.getId())
                .nickname(challenge.getProfile().getNickname())
                .startTime(challenge.getStartTime())
                .endTime(challenge.getEndTime())
                .challengeStatus(challenge.getChallengeStatus())
                .targetAmount(challenge.getTargetAmount())
                .usedAmount(challenge.getUsedAmount())
                .leftoverAmount(challenge.getLeftoverAmount())
                .isViewed(challenge.getIsViewed())
                .build();
    }
}
