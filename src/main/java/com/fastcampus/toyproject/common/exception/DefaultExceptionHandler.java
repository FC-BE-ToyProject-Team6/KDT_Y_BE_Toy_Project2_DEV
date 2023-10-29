package com.fastcampus.toyproject.common.exception;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.BAD_REQUEST;

import com.fastcampus.toyproject.common.dto.ErrorResponseDTO;
import com.fastcampus.toyproject.domain.itinerary.exception.ItineraryException;
import com.fastcampus.toyproject.domain.trip.exception.TripException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 예상한 예외들 잡는 곳
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(
        value = {TripException.class, ItineraryException.class, DefaultException.class})
    public ResponseEntity<ErrorResponseDTO> handleDefaultException(
        DefaultException e,
        HttpServletRequest request
    ) {
        log.error("error!!!! errorCode : {}, url : {}, message : {}, trace : {}",
            e.getErrorCode(),
            e.getErrorMsg(),
            request.getRequestURI(),
            e.getStackTrace()
        );

        return new ResponseEntity<>(
            ErrorResponseDTO.error(
                e.getErrorCode(),
                e.getMessage()),
            HttpStatus.CONFLICT
        );
    }

    /**
     * 커스텀 에러를 제외한 badRequset 만 중점으로 처리하는 에러 핸들러
     *
     * @param
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
        HttpRequestMethodNotSupportedException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        InvalidFormatException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(
        Exception e, HttpServletRequest request
    ) {
        log.error("request error url : {}, message : {}",
            request.getRequestURI(),
            e.getMessage()
        );

        return new ResponseEntity<>(
            ErrorResponseDTO.error(
                BAD_REQUEST,
                BAD_REQUEST.getMsg()
            ),
            HttpStatus.BAD_REQUEST

        );
    }

    @ExceptionHandler(value = {
        Exception.class
    })
    public ResponseEntity<ErrorResponseDTO> handleException(
        Exception e, HttpServletRequest request
    ) {
        log.error("exception error class : {}, url : {}, message : {}, trace : {}",
            e.getClass(),
            request.getRequestURI(),
            e.getMessage(),
            e.getStackTrace()
        );

        return new ResponseEntity<>(
            ErrorResponseDTO.error(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
