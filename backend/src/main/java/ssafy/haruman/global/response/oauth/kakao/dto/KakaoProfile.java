package ssafy.haruman.global.response.oauth.kakao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfile {

    private String nickname;
    private String thumbnailImageUrl;
    private String profileImageUrl;
    private boolean isDefaultImage;
}