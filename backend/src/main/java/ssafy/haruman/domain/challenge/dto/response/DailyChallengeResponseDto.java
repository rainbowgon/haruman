package ssafy.haruman.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyChallengeResponseDto {

    private Integer participantCount;
    private Long challengeId;
    private Integer targetAmount;
    private Integer usedAmount;
    private Integer leftoverAmount;
    private ChallengeStatus challengeStatus;

    public static DailyChallengeResponseDto from(Challenge challenge, Integer participantCount) {
        return DailyChallengeResponseDto.builder()
                .participantCount(participantCount)
                .challengeId(challenge.getId())
                .targetAmount(challenge.getTargetAmount())
                .usedAmount(challenge.getUsedAmount())
                .leftoverAmount(challenge.getLeftoverAmount())
                .challengeStatus(challenge.getChallengeStatus())
                .build();
    }

    public static DailyChallengeResponseDto from(Integer participantCount) {
        return DailyChallengeResponseDto.builder()
                .participantCount(participantCount)
                .challengeId(0L)
                .targetAmount(10000)
                .usedAmount(0)
                .leftoverAmount(0)
                .challengeStatus(ChallengeStatus.READY)
                .build();
    }

}
