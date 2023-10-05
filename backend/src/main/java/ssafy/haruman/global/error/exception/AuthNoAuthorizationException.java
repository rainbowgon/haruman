package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class AuthNoAuthorizationException extends CustomException {

    public static final CustomException EXCEPTION = new AuthNoAuthorizationException();

    public AuthNoAuthorizationException() {
        super(GlobalErrorCode.AUTH_NO_AUTHORIZATION);
    }
}
