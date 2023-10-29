package com.fastcampus.toyproject.common.dto;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.INTERNAL_SERVER_ERROR;

import com.fastcampus.toyproject.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {

    private final ExceptionCode exceptionCode;
    private final String errorMsg;

    public static ErrorResponseDTO error(ExceptionCode exceptionCode, String errorMsg) {
        return new ErrorResponseDTO(exceptionCode, errorMsg);
    }

    public static ErrorResponseDTO error() {
        return new ErrorResponseDTO(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getMsg());
    }


}
