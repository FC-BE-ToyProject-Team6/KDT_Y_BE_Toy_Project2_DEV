package com.fastcampus.toyproject.common.exception;

import lombok.Getter;

@Getter
public class DefaultException extends RuntimeException {

    ExceptionCode errorCode;
    String errorMsg;

    public DefaultException(ExceptionCode errorCode, String errorMsg) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DefaultException(ExceptionCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMsg();
    }
}
