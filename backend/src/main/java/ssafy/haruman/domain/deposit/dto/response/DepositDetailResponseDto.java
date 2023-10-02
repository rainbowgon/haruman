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
public class DepositDetailResponseDto {

    private String bank;
    private String name;
    private String description;
    private BigDecimal interestRate;


    public static DepositDetailResponseDto from(Deposit deposit) {
        return DepositDetailResponseDto.builder()
                .bank(deposit.getBank())
                .name(deposit.getName())
                .description(deposit.getDescription())
                .interestRate(deposit.getInterestRate())
                .build();
    }
}
