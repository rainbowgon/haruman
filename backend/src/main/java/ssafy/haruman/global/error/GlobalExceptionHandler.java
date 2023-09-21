package ssafy.haruman.global.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ssafy.haruman.global.error.dto.ErrorReason;
import ssafy.haruman.global.error.dto.ErrorResponse;
import ssafy.haruman.global.error.errorCode.BaseErrorCode;
import ssafy.haruman.global.error.errorCode.GlobalErrorCode;
import ssafy.haruman.global.error.exception.CustomException;
import ssafy.haruman.global.mattermost.NotificationManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final NotificationManager notificationManager;

    @ExceptionHandler(CustomException.class) // business Exception
    public ResponseEntity<ErrorResponse> handleBusinessException(CustomException e, HttpServletRequest request) {
        BaseErrorCode code = e.getErrorCode();
        ErrorReason errorReason = code.getErrorReason();
        return ResponseEntity.status(errorReason.getStatus())
                .body(ErrorResponse.of(errorReason, request.getRequestURL().toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) throws IOException {
        sendMattermost(e, request);
        GlobalErrorCode errorCode = GlobalErrorCode.CUSTOM_INTERNAL_SERVER_ERROR;
        ErrorReason errorReason = errorCode.getErrorReason();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorReason, request.getRequestURL().toString()));
    }

    private void sendMattermost(Exception e, HttpServletRequest req) {
        notificationManager.sendNotification(e, req.getRequestURI(), getParams(req));
    }

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append("\n");
        }
        return params.toString();
    }
}