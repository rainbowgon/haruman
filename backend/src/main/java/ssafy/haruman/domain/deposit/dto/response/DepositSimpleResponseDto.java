package ssafy.haruman.domain.deposit.dto.response;

import lombok.*;
import ssafy.haruman.domain.deposit.entity.Deposit;

import java.math.BigDecimal;

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
