package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class GoogleAccessTokenFailureException extends CustomException {

    public static final CustomException EXCEPTION = new GoogleAccessTokenFailureException();

    public GoogleAccessTokenFailureException() {
        super(GlobalErrorCode.GOOGLE_ACCESS_TOKEN_FAILURE);
    }
}
