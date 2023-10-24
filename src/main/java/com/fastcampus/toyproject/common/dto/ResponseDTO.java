package com.fastcampus.toyproject.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ResponseDTO<T> {
    private final HttpStatus status;
    private final String msg;
    private final T data;

    public static <T> ResponseDTO<T> ok(String msg, T data) {
        return new ResponseDTO<>(HttpStatus.OK, msg, data);
    }

    public static <T> ResponseDTO<Void> ok(String msg) {
        return new ResponseDTO<>(HttpStatus.OK, msg, null);
    }

    public static <T> ResponseDTO<Void> ok() {
        return new ResponseDTO<>(HttpStatus.OK, null, null);
    }


}
