package com.fastcampus.toyproject.common.dto;

import com.fastcampus.toyproject.common.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.INTERNAL_SERVER_ERROR;
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


    // 사용자 지정 메시지를 위한 오버로드된 메서드 추가
    public static ErrorResponseDTO error(String errorMsg) {
        return new ErrorResponseDTO(INTERNAL_SERVER_ERROR, errorMsg);
    }
}
