package ssafy.haruman.domain.challenge.dto.response;

import lombok.*;
import ssafy.haruman.domain.challenge.entity.Expense;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExpenseResponseDto {

    private Long id;
    private Long challengeId;
    private String categoryName;
    private String categoryColor;
    private LocalDateTime payTime;
    private Integer payAmount;
    private String content;

    public static ExpenseResponseDto from(Expense expense) {
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .challengeId(expense.getChallenge().getId())
                .categoryName(expense.getCategory().getName())
                .categoryColor(expense.getCategory().getColor().toString())
                .payTime(expense.getPayTime())
                .payAmount(expense.getPayAmount())
                .content(expense.getContent())
                .build();
    }
}
