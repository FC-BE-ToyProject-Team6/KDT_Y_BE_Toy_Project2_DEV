package com.fastcampus.toyproject.common.util;

import com.fastcampus.toyproject.common.exception.DefaultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void 날짜_차이_계산() {
        LocalDate startD = LocalDate.of(2022, 11, 22);
        LocalDate endD = LocalDate.of(2023, 10, 22);

        LocalDateTime startDt = LocalDateTime.of(2023, 10, 26, 00, 40, 0);
        LocalDateTime endDt = LocalDateTime.now();

        System.out.println("날짜 차이: " + DateUtil.getDaysBetweenDate(startD, endD));
        System.out.println("날짜 차이: " + DateUtil.getDaysBetweenDate(startDt, endDt));
        System.out.println("시간 차이: " + DateUtil.getTimeBetweenDate(startDt, endDt));

    }

    @Test
    void 출발날짜가_도착날짜보다_늦으면_에러나는지_확인_LocalDate버전() {
        try {
            LocalDate startD = LocalDate.of(2022, 11, 22);
            LocalDate endD = LocalDate.of(2023, 10, 22);

            DateUtil.isStartDateEarlierThanEndDate(endD, startD);
        } catch (DefaultException e) {
            System.out.println(e.getErrorCode() + ": " + e.getErrorMsg());
        }
    }

    @Test
    void 출발날짜가_도착날짜보다_늦으면_에러나는지_확인_LocalDateTime버전() {
        try {
            LocalDateTime startDt = LocalDateTime.of(2023, 10, 26, 00, 40, 0);
            LocalDateTime endDt = LocalDateTime.now();

            //DateUtil.isStartDateEarlierThanEndDate(startDt, endDt);
            DateUtil.isStartDateEarlierThanEndDate(endDt, startDt);
        } catch (DefaultException e) {
            System.out.println(e.getErrorCode() + ": " + e.getErrorMsg());
        }
    }

}
