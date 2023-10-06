package ssafy.haruman.global.error.exception;

import ssafy.haruman.global.error.errorCode.GlobalErrorCode;

public class ExpenseNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ExpenseNotFoundException();

    public ExpenseNotFoundException() {
        super(GlobalErrorCode.EXPENSE_NOT_FOUND);
    }
    
}
