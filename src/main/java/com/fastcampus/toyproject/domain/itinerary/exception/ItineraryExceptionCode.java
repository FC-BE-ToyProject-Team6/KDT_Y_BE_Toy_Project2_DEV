package com.fastcampus.toyproject.domain.itinerary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItineraryExceptionCode {

    NO_ITINERARY("해당되는 여정이 없습니다."),
    INCORRECT_ITNERARY_ORDER("잘못된 여정 순서입니다."),
    DUPLICATE_ITINERARY_ORDER("여정 순서가 중복됩니다."),
    EMPTY_ITINERARY("수정 할 여정 정보가 없습니다."),
    ILLEGAL_ARGUMENT_DEPARTUREPLACE("출발지를 입력하지 않았습니다."),
    ILLEGAL_ARGUMENT_ARRIVALPLACE("도착지를 입력하지 않았습니다."),
    ILLEGAL_ITINERARY_TYPE("잘 못 된 여정 타입입니다."),
    ITINERARY_ALREADY_DELETED("이미 삭제된 여정입니다.");
    private final String msg;

}
