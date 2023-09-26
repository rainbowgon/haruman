package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class MemberTokenExpired extends CustomException {

    public static final CustomException EXCEPTION = new MemberTokenExpired();

    public MemberTokenExpired() {
        super(GlobalErrorCode.MEMBER_TOKEN_EXPIRED);
    }
}
