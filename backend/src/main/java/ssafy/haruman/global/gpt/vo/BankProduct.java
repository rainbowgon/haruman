package ssafy.haruman.global.gpt.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

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
        String fullString = "{ bank=" + bank + ", name=" + name + ", description=" + description + ", interest_rate=" + interest_rate + " }";
        return new String(fullString.getBytes(), StandardCharsets.UTF_8);
    }
}
