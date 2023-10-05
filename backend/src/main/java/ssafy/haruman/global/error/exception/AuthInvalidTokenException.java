package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class AuthInvalidTokenException extends CustomException {

    public static final CustomException EXCEPTION = new AuthInvalidTokenException();

    public AuthInvalidTokenException() {
        super(GlobalErrorCode.AUTH_INVALID_TOKEN);
    }
}
