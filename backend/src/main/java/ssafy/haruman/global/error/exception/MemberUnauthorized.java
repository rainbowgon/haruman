package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class MemberUnauthorized extends CustomException {

    public static final CustomException EXCEPTION = new CategoryNotFoundException();

    public MemberUnauthorized() {
        super(GlobalErrorCode.MEMBER_UNAUTHORIZED);
    }
}
