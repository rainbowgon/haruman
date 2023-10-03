package ssafy.haruman.domain.deposit.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositCreateRequestDto {

    private String bank;
    private String name;
    private String description;
    private BigDecimal interestRate;

}
