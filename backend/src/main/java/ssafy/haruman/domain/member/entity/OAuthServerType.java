package ssafy.haruman.domain.member.entity;

import java.util.Locale;

public enum OAuthServerType {

    DEFAULT,
    KAKAO,
    GOOGLE,
    ;

    public static OAuthServerType fromName(String source) {
        return OAuthServerType.valueOf(source.toUpperCase(Locale.ENGLISH));
    }
}
