package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class CategoryDuplicateException extends CustomException {

    public CategoryDuplicateException() {
        super(GlobalErrorCode.CATEGORY_DUPLICATION);
    }
}
