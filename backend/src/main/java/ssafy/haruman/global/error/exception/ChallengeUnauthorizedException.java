package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ChallengeUnauthorizedException extends CustomException {

    public static final CustomException EXCEPTION = new ChallengeUnauthorizedException();

    public ChallengeUnauthorizedException() {
        super(GlobalErrorCode.CHALLENGE_UNAUTHORIZED);
    }
}
