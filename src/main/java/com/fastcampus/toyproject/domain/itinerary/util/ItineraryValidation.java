package com.fastcampus.toyproject.domain.itinerary.util;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;

import java.util.*;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.DUPLICATE_ITINERARY_ORDER;
import static com.fastcampus.toyproject.common.exception.ExceptionCode.INCORRECT_ORDER;

public class ItineraryValidation {

    /**
     * 여정 순서가 적절하게 입력되었는지 확인하는 메소드
     * @author : 지운
     * @param : itineraryList
     * @return
     */
    public static void validateItinerariesOrder(List<Itinerary> itineraryList) {
        //1. 순서가 중복되는지 검사하고, 순서대로 정렬. (O(NlogN))
        Collections.sort(itineraryList, (o1, o2) -> {
            if (o1.getItineraryOrder() == o2.getItineraryOrder()) {
                throw new DefaultException(DUPLICATE_ITINERARY_ORDER);
            }
            return o1.getItineraryOrder()- o2.getItineraryOrder();
        });

        isItinerarySorted(itineraryList);
    }

    private static void isItinerarySorted(List<Itinerary> itineraryList) {
        //2. 순서가 1부터 차례대로 들어갔는지 확인. (O(N))
        for (int orderIdx = 1; orderIdx <= itineraryList.size(); orderIdx++) {
            if (itineraryList.get(orderIdx - 1).getItineraryOrder() != orderIdx) {
                throw new DefaultException(INCORRECT_ORDER);
            }
        }
    }


}
