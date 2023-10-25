package com.fastcampus.toyproject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {
    NO_ITINERARY("해당되는 여정이 없습니다."),
    DUPLICATE_ITINERARY_OEDER("여정 순서가 중복됩니다."),
    INCORRECT_ORDER("잘못된 여정 순서입니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    NO_SUCH_TRIP("해당하는 Trip이 없습니다."),

    ;

    private String msg;
}
