package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class CategoryDuplicateException extends CustomException {

    public static final CustomException EXCEPTION = new CategoryDuplicateException();

    public CategoryDuplicateException() {
        super(GlobalErrorCode.CATEGORY_DUPLICATION);
    }
}
