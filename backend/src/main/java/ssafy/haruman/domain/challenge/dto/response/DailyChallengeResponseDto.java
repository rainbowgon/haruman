package ssafy.haruman.domain.challenge.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.entity.Challenge;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyChallengeResponseDto {

    private Integer leftoverAmount;
    private List<ExpenseResponseDto> expenseList;

    public static DailyChallengeResponseDto from(Challenge challenge,
            List<ExpenseResponseDto> expenseList) {
        return DailyChallengeResponseDto.builder()
                .leftoverAmount(challenge.getLeftoverAmount())
                .expenseList(expenseList)
                .build();
    }
}
