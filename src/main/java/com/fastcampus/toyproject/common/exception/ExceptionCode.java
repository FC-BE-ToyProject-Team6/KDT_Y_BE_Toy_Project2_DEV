package com.fastcampus.toyproject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {

    INCORRECT_ORDER("잘못된 여정 순서입니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    BAD_REQUEST("잘못된 입력값 입니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    ;

    private final String msg;
}
