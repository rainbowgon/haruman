package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class GoogleUserInfoFailureException extends CustomException {

    public static final CustomException EXCEPTION = new GoogleUserInfoFailureException();

    public GoogleUserInfoFailureException() {
        super(GlobalErrorCode.GOOGLE_USER_INFO_FAILURE);
    }
}
