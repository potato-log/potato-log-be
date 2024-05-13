package site.potatolog.potatolog.user.exception;

import site.potatolog.potatolog.common.exception.CustomException;
import site.potatolog.potatolog.common.exception.ErrorCode;

// 예시 코드 나중에 지울 예정
public class UserException extends CustomException {
  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }

  public static class UserNotFoundException extends UserException {
    public UserNotFoundException() {
      super(ErrorCode.USER_NOT_FOUND);
    }
  }
}
