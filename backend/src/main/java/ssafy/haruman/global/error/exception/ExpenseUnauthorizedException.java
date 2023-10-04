package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ExpenseUnauthorizedException extends CustomException {

    public static final CustomException EXCEPTION = new ExpenseUnauthorizedException();

    public ExpenseUnauthorizedException() {
        super(GlobalErrorCode.EXPENSE_UNAUTHORIZED);
    }
}
