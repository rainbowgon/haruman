package ssafy.haruman.domain.deposit.dto.response;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.deposit.entity.Deposit;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DepositSimpleResponseDto {

    private String bank;
    private String name;
    private BigDecimal interestRate;

    public static DepositSimpleResponseDto from(Deposit deposit) {
        return DepositSimpleResponseDto.builder()
                .bank(deposit.getBank())
                .name(deposit.getName())
                .interestRate(deposit.getInterestRate())
                .build();
    }
}
