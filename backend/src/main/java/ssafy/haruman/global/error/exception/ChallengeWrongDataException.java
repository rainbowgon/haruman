package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ChallengeWrongDataException extends CustomException {

    public static final CustomException EXCEPTION = new ChallengeWrongDataException();

    public ChallengeWrongDataException() {
        super(GlobalErrorCode.CHALLENGE_WRONG_DATA);
    }

}
