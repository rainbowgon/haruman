package ssafy.haruman.global.error.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReason {

    private final HttpStatus status;
    private final String code;
    private final String reason;
}