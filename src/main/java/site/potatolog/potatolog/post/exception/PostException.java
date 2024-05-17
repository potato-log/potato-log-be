package site.potatolog.potatolog.post.exception;

import site.potatolog.potatolog.common.exception.CustomException;
import site.potatolog.potatolog.common.exception.ErrorCode;

public class PostException extends CustomException {
    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class PostNotFoundException extends PostException {
        public PostNotFoundException() {
            super(ErrorCode.POST_NOT_FOUND);
        }
    }

    public static class UserNotFoundException extends PostException {
        public UserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }
    }
}
