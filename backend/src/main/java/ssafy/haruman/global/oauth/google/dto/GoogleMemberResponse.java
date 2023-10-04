package ssafy.haruman.global.oauth.google.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class GoogleMemberResponse {

    private String id;
    private String email;
    private String verifiedEmail;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;
    private String locale;
}
