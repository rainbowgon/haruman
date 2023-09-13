package ssafy.haruman.domain.challenge.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseUpdateRequestDto {

    private Long ExpenseId;
    private Long categoryId;
    private Integer payAmount;
    private String content;

}
