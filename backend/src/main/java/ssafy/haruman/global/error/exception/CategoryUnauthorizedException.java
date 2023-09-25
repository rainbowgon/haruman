package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class CategoryUnauthorizedException extends CustomException {

    public static final CustomException EXCEPTION = new CategoryUnauthorizedException();

    public CategoryUnauthorizedException() {
        super(GlobalErrorCode.CATEGORY_UNAUTHORIZED);
    }
}
