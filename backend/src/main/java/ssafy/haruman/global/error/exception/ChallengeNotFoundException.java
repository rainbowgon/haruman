package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ChallengeNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ChallengeNotFoundException();

    public ChallengeNotFoundException() {
        super(GlobalErrorCode.CHALLENGE_NOT_FOUND);
    }

}
