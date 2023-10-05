package ssafy.haruman.global.error.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssafy.haruman.global.error.dto.ErrorReason;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    /**
     * 예시
     */
    EXAMPLE_NOT_FOUND(NOT_FOUND, "EXAMPLE_404_1", "예시를 찾을 수 없는 오류입니다."),

    CUSTOM_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL_500_1", "서버 오류. 관리자에게 문의 부탁드립니다."),

    /* 프로필 */
    PROFILE_NOT_FOUND(NOT_FOUND, "PROFILE-001", "해당 ID의 프로필이 없습니다."),
    MEMBER_PROFILE_NOT_FOUND(NOT_FOUND, "PROFILE-002", "해당 멤버의 프로필이 없습니다."),

    /* 챌린지 */
    CHALLENGE_NOT_FOUND(NOT_FOUND, "CHALLENGE-001", "해당 ID의 챌린지가 없습니다."),
    CHALLENGE_ALREADY_EXISTS(METHOD_NOT_ALLOWED, "CHALLENGE-002", "해당 사용자의 챌린지가 이미 존재합니다."),
    CHALLENGE_NOT_AVAILABLE(METHOD_NOT_ALLOWED, "CHALLENGE-003", "해당 시간에는 챌린지 시작이 불가합니다."),
    CHALLENGE_WRONG_DATA(INTERNAL_SERVER_ERROR, "CHALLENGE-004", "잘못된 데이터가 포함된 챌린지입니다."),
    CHALLENGE_UNAUTHORIZED(UNAUTHORIZED, "CHALLENGE-005", "해당 챌린지에 접근 권한이 없습니다."),

    /* 지출내역 */
    EXPENSE_NOT_FOUND(NOT_FOUND, "EXPENSE-001", "해당 ID의 지출내역이 없습니다."),
    EXPENSE_UNAUTHORIZED(UNAUTHORIZED, "EXPENSE-002", "해당 지출내역에 접근 권한이 없습니다."),
    EXPENSE_NOT_ALLOWED(METHOD_NOT_ALLOWED, "EXPENSE-003", "이미 종료된 챌린지의 지출 내역은 생성,수정,삭제가 불가합니다."),

    /* 카테고리 */
    CATEGORY_NOT_FOUND(NOT_FOUND, "CATEGORY-001", "해당 ID의 카테고리가 없습니다."),
    CATEGORY_DUPLICATION(CONFLICT, "CATEGORY-002", "같은 이름의 카테고리가 이미 존재합니다."),
    CATEGORY_UNAUTHORIZED(UNAUTHORIZED, "CATEGORY-003", "해당 카테고리에 접근 권한이 없습니다."),

    /* OAUTH */
    OAUTH_NOT_FOUND(NOT_FOUND, "OAUTH-001", "지원하지 않는 소셜 로그인 타입입니다."),
    GOOGLE_ACCESS_TOKEN_FAILURE(INTERNAL_SERVER_ERROR, "OAUTH-002", "구글의 Access Token을 받아오지 못했습니다."),
    GOOGLE_USER_INFO_FAILURE(INTERNAL_SERVER_ERROR, "OAUTH-003", "구글의 사용자 정보를 받아오지 못했습니다."),
    KAKAO_ACCESS_TOKEN_FAILURE(INTERNAL_SERVER_ERROR, "OAUTH-004", "카카오의 Access Token을 받아오지 못했습니다."),
    KAKAO_USER_INFO_FAILURE(INTERNAL_SERVER_ERROR, "OAUTH-005", "카카오의 사용자 정보를 받아오지 못했습니다."),

    /* 멤버 */
    MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER-001", "주어진 ID에 해당하는 Member가 없습니다."),

    /* AUTH */
    AUTH_NO_AUTHORIZATION(FORBIDDEN, "AUTH-001", "Header에 Authorization Code가 없습니다."),
    AUTH_INVALID_AUTHORIZATION_FORMAT(FORBIDDEN, "AUTH-002", "Header에서 Authorizaion Code가 Bearer로 시작하지 않습니다."),
    AUTH_INVALID_TOKEN(FORBIDDEN, "AUTH-003", "토큰이 유효하지 않습니다."),
    AUTH_TOKEN_EXPIRED(UNAUTHORIZED, "AUTH-004", "토큰이 만료되었습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
