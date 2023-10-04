package ssafy.haruman.domain.deposit.dto.response;


import lombok.*;
import ssafy.haruman.domain.deposit.entity.Deposit;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DepositDetailResponseDto {

    private String bank;
    private String name;
    private String description;
    private float interestRate;


    public static DepositDetailResponseDto from(Deposit deposit) {
        return DepositDetailResponseDto.builder()
                .bank(deposit.getBank())
                .name(deposit.getName())
                .description(deposit.getDescription())
                .interestRate(deposit.getInterestRate())
                .build();
    }
}
