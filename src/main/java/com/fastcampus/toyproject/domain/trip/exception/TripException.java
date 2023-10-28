package com.fastcampus.toyproject.domain.trip.exception;


import lombok.Getter;

@Getter
public class TripException extends RuntimeException {

    TripExceptionCode errorCode;
    String errorMsg;

    public TripException(TripExceptionCode errorCode, String errorMsg) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public TripException(TripExceptionCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMsg();
    }
}
