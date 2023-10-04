package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ExpenseNotAllowedException extends CustomException {

    public static final CustomException EXCEPTION = new ExpenseNotAllowedException();

    public ExpenseNotAllowedException() {
        super(GlobalErrorCode.EXPENSE_NOT_ALLOWED);
    }
}
