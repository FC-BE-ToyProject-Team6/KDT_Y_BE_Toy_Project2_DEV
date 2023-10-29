package com.fastcampus.toyproject.domain.itinerary.util;

import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.DUPLICATE_ITINERARY_ORDER;
import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.INCORRECT_ITNERARY_ORDER;

import com.fastcampus.toyproject.common.exception.DefaultException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItineraryOrderUtilTest {

    LocalDateTime testStartDate, testEndDate;
    List<Integer> testList1, testList2;

    public ItineraryOrderUtilTest() {
        this.testList1 = new ArrayList<>();
        this.testList2 = new ArrayList<>();
        this.testStartDate = LocalDateTime.now();
        this.testEndDate = LocalDateTime.now();
    }

    @BeforeEach
    void setUp() {
//        testList1.add(Itinerary.builder().type(1).name("비행기").order(1).startDate(testStartDate).endDate(testEndDate).build());
//        testList1.add(Itinerary.builder().type(2).name("호텔").order(2).startDate(testStartDate).endDate(testEndDate).build());
//        testList1.add(ItineraryReqeust.builder().type(3).name("롯데월드").order(2).startDate(testStartDate).endDate(testEndDate).build());
//        testList1.add(ItineraryReqeust.builder().type(2).name("호텔").order(3).startDate(testStartDate).endDate(testEndDate).build());
//
//        testList2.add(ItineraryReqeust.builder().type(1).name("비행기").order(1).startDate(testStartDate).endDate(testEndDate).build());
//        testList2.add(ItineraryReqeust.builder().type(2).name("호텔").order(10).startDate(testStartDate).endDate(testEndDate).build());
//        testList2.add(ItineraryReqeust.builder().type(3).name("롯데월드").order(3).startDate(testStartDate).endDate(testEndDate).build());
//        testList2.add(ItineraryReqeust.builder().type(2).name("호텔").order(4).startDate(testStartDate).endDate(testEndDate).build());
    }

    @Test
    void 순서가_제대로_검증이_되는지_확인() {
        //given

        //when 1) 중복 순서 검사
        try {
            ItineraryOrderUtil.validateItinerariesOrder(testList1);
        } catch (DefaultException e) {
            //where
            Assertions.assertEquals(DUPLICATE_ITINERARY_ORDER, e.getErrorCode());
        }

        //when 2) 여정 번호 순서대로인지 검사
        try {
            ItineraryOrderUtil.validateItinerariesOrder(testList2);
        } catch (DefaultException e) {
            //where
            Assertions.assertEquals(INCORRECT_ITNERARY_ORDER, e.getErrorCode());
        }
    }

}
