package ssafy.haruman.domain.challenge.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseCreateRequestDto {

    private Long categoryId;
    private LocalDateTime payTime;
    private Integer payAmount;
    private String content;

}
