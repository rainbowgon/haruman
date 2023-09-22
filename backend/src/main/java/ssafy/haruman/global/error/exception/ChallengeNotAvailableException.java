package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ChallengeNotAvailableException extends CustomException {

    public static final CustomException EXCEPTION = new ChallengeNotAvailableException();

    public ChallengeNotAvailableException() {
        super(GlobalErrorCode.CHALLENGE_NOT_AVAILABLE);
    }

}
