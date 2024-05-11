package site.potatolog.potatolog.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
  private int statusCode;
  private String message;

  public ErrorResponse(HttpStatus status, String message) {
    this.statusCode = status.value();
    this.message = message;
  }
}
