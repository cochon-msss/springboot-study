package me.sonminseong.springbootblogdeveloper.config.error.execption;

import me.sonminseong.springbootblogdeveloper.config.error.ErrorCode;

public class BusinessBaseException extends RuntimeException {
    /**
     * 비즈니스 로직을 작성하다 발생하는 예외를 모아둘 최상위 클래스
     */
    private final ErrorCode errorCode;

    public BusinessBaseException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessBaseException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
}
