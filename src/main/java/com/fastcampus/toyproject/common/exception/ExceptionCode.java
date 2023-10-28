package com.fastcampus.toyproject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {


    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    BAD_REQUEST("잘못된 입력값 입니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    STARTDATE_IS_LATER_THAN_ENDDATE("출발 일정이 도착 일정보다 늦습니다."),

    ;

    private final String msg;
}
