package site.potatolog.potatolog.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> {
    private static final String SUCCESS_MESSAGE = "success";

    private int statusCode;
    private String message;
    private T data;

    private CommonResponse(HttpStatus status, String message, T data) {
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
    }

    public static <S> CommonResponse<S> ok(S data) {
        return new CommonResponse<>(HttpStatus.OK, SUCCESS_MESSAGE, data);
    }

    public static <S> CommonResponse<S> created(S data) {
        return new CommonResponse<>(HttpStatus.CREATED, SUCCESS_MESSAGE, data);
    }

    public static CommonResponse<Void> error(HttpStatus status, String message) {
        return new CommonResponse<>(status, message, null);
    }
}
