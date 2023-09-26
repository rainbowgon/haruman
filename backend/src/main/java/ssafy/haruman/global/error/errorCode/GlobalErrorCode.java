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
    EXPENSE_NOT_FOUND(NOT_FOUND, "CHALLENGE-002", "해당 ID의 지출내역이 없습니다."),
    CHALLENGE_ALREADY_EXISTS(METHOD_NOT_ALLOWED, "CHALLENGE-003", "해당 사용자의 챌린지가 이미 존재합니다."),
    CHALLENGE_NOT_AVAILABLE(METHOD_NOT_ALLOWED, "CHALLENGE-004", "해당 시간에는 챌린지 시작이 불가합니다."),

    /* 카테고리 */
    CATEGORY_NOT_FOUND(NOT_FOUND, "CATEGRORY_404_1", "해당 ID의 카테고리가 없습니다."),
    CATEGORY_DUPLICATION(CONFLICT, "CATEGORY-002", "같은 이름의 카테고리가 이미 존재합니다."),
    CATEGORY_UNAUTHORIZED(UNAUTHORIZED, "CATEGORY-003", "해당 카테고리에 접근 권한이 없습니다."),

    /* OAUTH */
    OAUTH_NOT_FOUND(NOT_FOUND, "OAUTH-001", "지원하지 않는 소셜 로그인 타입입니다."),

    /* 멤버 */
    MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER-001", "주어진 ID에 해당하는 Member가 없습니다."),
    MEMBER_NO_AUTHORIZATION(FORBIDDEN, "MEMBER-002", "Authorization Code가 없습니다."),
    MEMBER_UNAUTHORIZED(UNAUTHORIZED, "MEMBER-003", "인증되지 않은 요청입니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
