package ssafy.haruman.domain.challenge.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.entity.Expense;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExpenseResponseDto {

    private Long id;
    private Long challengeId;
    private String categoryName;
    private LocalDateTime payTime;
    private int payAmount;
    private String content;

    public static ExpenseResponseDto from(Expense expense) {
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .challengeId(expense.getChallenge().getId())
                .categoryName(expense.getCategory().getName())
                .payTime(expense.getPayTime())
                .payAmount(expense.getPayAmount())
                .content(expense.getContent())
                .build();
    }
}
