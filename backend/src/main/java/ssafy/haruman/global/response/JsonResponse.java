package ssafy.haruman.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class JsonResponse {

    public static ResponseEntity<ResponseWrapper<Nullable>> ok(String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(null, message, null);
        return ResponseEntity.ok(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, message, data);
        return ResponseEntity.ok(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(String message, T data, PageInfo pageInfo) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(pageInfo, message, data);
        return ResponseEntity.ok(responseWrapper);
    }

    public static ResponseEntity<ResponseWrapper<Nullable>> of(HttpStatus httpStatus, String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(null, message, null);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> of(HttpStatus httpStatus, String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(null, message, data);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> of(HttpStatus httpStatus, String message, T data, PageInfo pageInfo) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(pageInfo, message, data);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

}
