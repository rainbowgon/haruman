package ssafy.haruman.domain.challenge.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseUpdateRequestDto {

    private String category;
    private int payAmount;
    private String content;

}
