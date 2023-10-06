package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class CategoryNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(GlobalErrorCode.CATEGORY_NOT_FOUND);
    }
}
