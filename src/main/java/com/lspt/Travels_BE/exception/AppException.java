package com.lspt.Travels_BE.exception;

public class AppException extends RuntimeException{
    public AppException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;

    public ErrorCode getErrorCode(){
        return errorCode;
    }
    public void setErrorCode(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
