package com.fastcampus.toyproject.common.exception;

import com.fastcampus.toyproject.common.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 예상한 예외들 잡는 곳
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorResponseDTO> handleDefaultException(
            DefaultException e,
            HttpServletRequest request
    ) {
        log.error("error!!!! errorCode : {}, url : {}, message : {}",
                e.getErrorCode(),
                request.getRequestURI(),
                e.getErrorMsg()
        );

        return new ResponseEntity<>(
                ErrorResponseDTO.error(
                        e.getErrorCode(),
                        e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    /**
     * 커스텀 에러를 제외한 나머지 에러들 처리 (추후 수정 예정) -> badRequset 만 중점으로 받을지 고민됨
     * @param
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            Exception.class
    })
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(
            Exception e, HttpServletRequest request
    ) {
        log.error("error!!!! url : {}, message : {}",
                request.getRequestURI(),
                e.getMessage()
        );

        return new ResponseEntity<>(
                ErrorResponseDTO.error(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
