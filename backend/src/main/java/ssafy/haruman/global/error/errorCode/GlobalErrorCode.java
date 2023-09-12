package ssafy.haruman.global.error.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssafy.haruman.global.error.dto.ErrorReason;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    EXAMPLE_NOT_FOUND(NOT_FOUND, "EXAMPLE_404_1", "예시를 찾을 수 없는 오류입니다."),

    CUSTOM_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL_500_1", "서버 오류. 관리자에게 문의 부탁드립니다."),

    CATEGORY_NOT_FOUND(NOT_FOUND, "CATEGRORY_404_1", "해당 ID의 카테고리가 없습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
