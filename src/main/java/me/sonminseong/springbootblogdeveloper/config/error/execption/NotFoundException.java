package me.sonminseong.springbootblogdeveloper.config.error.execption;

import me.sonminseong.springbootblogdeveloper.config.error.ErrorCode;

public class NotFoundException  extends BusinessBaseException{
    public NotFoundException(ErrorCode errorCode){
        super(errorCode.getMessage(), errorCode);
    }

    public NotFoundException(){
        super(ErrorCode.NOT_FOUND);
    }
}
