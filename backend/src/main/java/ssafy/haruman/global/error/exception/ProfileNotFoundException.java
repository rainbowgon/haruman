package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ProfileNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ProfileNotFoundException();

    public ProfileNotFoundException() {
        super(GlobalErrorCode.PROFILE_NOT_FOUND);
    }
}
