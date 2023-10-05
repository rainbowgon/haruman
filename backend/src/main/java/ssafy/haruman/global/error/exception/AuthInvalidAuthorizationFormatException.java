package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class AuthInvalidAuthorizationFormatException extends CustomException {

    public static final CustomException EXCEPTION = new AuthInvalidAuthorizationFormatException();

    public AuthInvalidAuthorizationFormatException() {
        super(GlobalErrorCode.AUTH_INVALID_AUTHORIZATION_FORMAT);
    }
}
