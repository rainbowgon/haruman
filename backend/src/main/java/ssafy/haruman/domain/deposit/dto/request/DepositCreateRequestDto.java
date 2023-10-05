package ssafy.haruman.domain.deposit.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositCreateRequestDto {

    private String bank;
    private String name;
    private String description;
    private float interestRate;

}
