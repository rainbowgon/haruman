package ssafy.haruman.global.gpt.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankProduct {

    private String bank;
    private String name;
    private String description;
    private Double interest_rate;

    @Override
    public String toString() {
        return String.format("bank=\'%s\', name=\'%s\', description=\'%s\', interest_rate=\'%f\' \n", bank, name, description, interest_rate);
    }
}
