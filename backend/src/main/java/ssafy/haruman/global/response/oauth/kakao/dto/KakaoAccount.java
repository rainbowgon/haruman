package ssafy.haruman.global.response.oauth.kakao.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoAccount {

    private boolean profileNeedsAgreement;
    private boolean profileNicknameNeedsAgreement;
    private boolean profileImageNeedsAgreement;
    private KakaoProfile profile;
    private boolean nameNeedsAgreement;
    private String name;
    private boolean emailNeedsAgreement;
    private boolean isEmailValid;
    private boolean isEmailVerified;
    private String email;
    private boolean ageRangeNeedsAgreement;
    private String ageRange;
    private boolean birthyearNeedsAgreement;
    private String birthyear;
    private boolean birthdayNeedsAgreement;
    private String birthday;
    private String birthdayType;
    private boolean genderNeedsAgreement;
    private String gender;
    private boolean phoneNumberNeedsAgreement;
    private String phoneNumber;
    private boolean ciNeedsAgreement;
    private String ci;
    private LocalDateTime ciAuthenticatedAt;
}

