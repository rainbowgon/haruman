package ssafy.haruman.global.oauth.google.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class GoogleToken {

    private String accessToken;
    private Integer expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
}
