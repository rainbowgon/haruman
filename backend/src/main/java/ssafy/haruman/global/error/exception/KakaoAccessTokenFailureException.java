package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class KakaoAccessTokenFailureException extends CustomException {

    public static final CustomException EXCEPTION = new KakaoAccessTokenFailureException();

    public KakaoAccessTokenFailureException() {
        super(GlobalErrorCode.KAKAO_ACCESS_TOKEN_FAILURE);
    }
}
