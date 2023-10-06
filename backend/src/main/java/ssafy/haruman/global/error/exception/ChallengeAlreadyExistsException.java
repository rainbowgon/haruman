package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ChallengeAlreadyExistsException extends CustomException {

    public static final CustomException EXCEPTION = new ChallengeAlreadyExistsException();

    public ChallengeAlreadyExistsException() {
        super(GlobalErrorCode.CHALLENGE_ALREADY_EXISTS);
    }

}
