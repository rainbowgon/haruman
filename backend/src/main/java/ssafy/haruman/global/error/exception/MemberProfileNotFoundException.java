package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class MemberProfileNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberProfileNotFoundException();

    public MemberProfileNotFoundException() {
        super(GlobalErrorCode.MEMBER_PROFILE_NOT_FOUND);
    }
}
