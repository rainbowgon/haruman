package ssafy.haruman.global.error.errorCode;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssafy.haruman.global.error.dto.ErrorReason;

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

    /* 카테고리 */
    CATEGORY_NOT_FOUND(NOT_FOUND, "CATEGORY-001", "해당 ID의 카테고리가 없습니다."),
    CATEGORY_DUPLICATION(CONFLICT, "CATEGORY-002", "같은 이름의 카테고리가 이미 존재합니다."),
    CATEGORY_UNAUTHORIZED(UNAUTHORIZED, "CATEGORY-003", "해당 카테고리에 접근 권한이 없습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
