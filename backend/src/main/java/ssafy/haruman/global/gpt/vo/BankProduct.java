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
        return "bank=" + bank + ", name=" + name + ", description=" + description + ", interest_rate=" + interest_rate;
    }
}
