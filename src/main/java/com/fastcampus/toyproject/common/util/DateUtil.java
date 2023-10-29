package com.fastcampus.toyproject.common.util;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.STARTDATE_IS_LATER_THAN_ENDDATE;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;

import com.fastcampus.toyproject.common.exception.DefaultException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 날짜 및 일시를 다루는 공통 유틸 클래스.
 * 기능
 * 1. 날짜 차이 계산 (10day)
 * 2. 시간 차이 계산 (1h 10m)
 * 3. 출발 날짜가 도착 날짜보다 늦지는 않은지.
 */
public class DateUtil {

    private static final String days = "days";
    private static final String hour = "h";
    private static final String min = "m";


    // 몇 일 여행인지 계산 -> LocalDate
    public static String getDaysBetweenDate(LocalDate start, LocalDate end) {
        return DAYS.toChronoUnit().between(start, end) + days;
    }

    // 몇 일 여행(숙박)인지 계산 -> LocalDateTime
    public static String getDaysBetweenDate(LocalDateTime start, LocalDateTime end) {
        return getDaysBetweenDate(start.toLocalDate(), end.toLocalDate());
    }

    // 몇 시간 소모되는지 계산 -> LocalDateTime
    public static String getTimeBetweenDate(LocalDateTime start, LocalDateTime end) {
        StringBuilder res = new StringBuilder();
        long hours = HOURS.toChronoUnit().between(start, end);
        long minutes = MINUTES.toChronoUnit().between(start, end);

        if (hours != 0) {
            res.append(hours).append(hour).append(" ");
            minutes -= hours * 60;
        }

        res.append(minutes).append(min);
        return res.toString();
    }

    // 출발 날짜가 도착 날짜보다 빠른지 검사 -> 아니라면 예외 날림
    public static void isStartDateEarlierThanEndDate(LocalDate start, LocalDate end) {
        if (!start.isEqual(end) && start.isAfter(end)) {
            throw new DefaultException(STARTDATE_IS_LATER_THAN_ENDDATE);
        }
    }

    public static void isStartDateEarlierThanEndDate(LocalDateTime start, LocalDateTime end) {
        if (!start.isEqual(end) && start.isAfter(end)) {
            throw new DefaultException(STARTDATE_IS_LATER_THAN_ENDDATE);
        }
    }


}
