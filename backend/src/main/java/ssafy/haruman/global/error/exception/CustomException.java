package ssafy.haruman.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssafy.haruman.global.error.dto.ErrorReason;
import ssafy.haruman.global.error.errorCode.BaseErrorCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReason getErrorReason() {
        return this.errorCode.getErrorReason();
    }
}