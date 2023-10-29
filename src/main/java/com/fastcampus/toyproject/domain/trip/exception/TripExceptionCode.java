package com.fastcampus.toyproject.domain.trip.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TripExceptionCode {
    NO_SUCH_TRIP("해당하는 Trip이 없습니다.");

    private final String msg;

}
