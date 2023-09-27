package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class MemberNoAuthorizationException extends CustomException {

    public static final CustomException EXCEPTION = new MemberNoAuthorizationException();

    public MemberNoAuthorizationException() {
        super(GlobalErrorCode.MEMBER_NO_AUTHORIZATION);
    }
}
