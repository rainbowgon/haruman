package ssafy.haruman.domain.challenge.dto.response;

import java.util.List;
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
    private Integer leftoverAmount;
    private ChallengeStatus challengeStatus;
    private List<ExpenseResponseDto> expenseList;

    public static DailyChallengeResponseDto from(Challenge challenge, Integer participantCount,
            List<ExpenseResponseDto> expenseList) {
        return DailyChallengeResponseDto.builder()
                .participantCount(participantCount)
                .leftoverAmount(challenge.getLeftoverAmount())
                .challengeStatus(challenge.getChallengeStatus())
                .expenseList(expenseList)
                .build();
    }
}
