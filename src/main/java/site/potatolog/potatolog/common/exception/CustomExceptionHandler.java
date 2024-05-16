package site.potatolog.potatolog.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.potatolog.potatolog.common.dto.CommonResponse;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<Void>> customExceptionHandler(CustomException e) {
        log.error("--------------------------------");
        log.error("StackTrace = {} ", (Object) e.getStackTrace());
        log.error("ErrorStatus = {} ", e.getErrorCode().getStatus());
        log.error("ErrorMessage = {} ", e.getErrorCode().getMessage());
        log.error("--------------------------------");

        CommonResponse<Void> commonResponseError = CommonResponse.error(
                e.getErrorCode().getStatus(),
                e.getErrorCode().getMessage()
        );

        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(commonResponseError);
    }
}
