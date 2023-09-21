package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class OAuthNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthNotFoundException();

    public OAuthNotFoundException() {
        super(GlobalErrorCode.OAUTH_NOT_FOUND);
    }
}
