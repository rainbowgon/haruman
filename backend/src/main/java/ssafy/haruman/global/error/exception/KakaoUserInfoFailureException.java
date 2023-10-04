package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class KakaoUserInfoFailureException extends CustomException {

    public static final CustomException EXCEPTION = new KakaoUserInfoFailureException();

    public KakaoUserInfoFailureException() {
        super(GlobalErrorCode.KAKAO_USER_INFO_FAILURE);
    }
}
