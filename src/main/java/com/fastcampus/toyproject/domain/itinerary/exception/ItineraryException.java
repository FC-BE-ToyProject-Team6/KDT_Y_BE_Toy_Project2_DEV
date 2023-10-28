package com.fastcampus.toyproject.domain.itinerary.exception;


import lombok.Getter;

@Getter
public class ItineraryException extends RuntimeException {

    ItineraryExceptionCode errorCode;
    String errorMsg;

    public ItineraryException(ItineraryExceptionCode errorCode, String errorMsg) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ItineraryException(ItineraryExceptionCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMsg();
    }
}
