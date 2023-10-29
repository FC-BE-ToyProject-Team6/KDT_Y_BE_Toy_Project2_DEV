package com.fastcampus.toyproject.domain.itinerary.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class ItineraryResponseTest {

    LocalDateTime testDate = LocalDateTime.now();

    @Test
    void 여정_응답_리스트_생성_테스트() {
        List<ItineraryResponse> list = new ArrayList<>();
        ItineraryResponse ir1 = MovementResponse.builder()
            .itineraryName("비행기").itineraryOrder(1).departureDate(testDate).arrivalDate(testDate)
            .departurePlace("인천").arrivalPlace("도쿄").build();
        ItineraryResponse ir2 = LodgementResponse.builder()
            .itineraryName("롯데호텔").itineraryOrder(2).checkIn(testDate).checkOut(testDate)
            .build();
        ItineraryResponse ir3 = StayResponse.builder()
            .itineraryName("비행기").itineraryOrder(3).departureDate(testDate).arrivalDate(testDate)
            .build();

        list.add(ir1);
        list.add(ir2);
        list.add(ir3);


    }


}
