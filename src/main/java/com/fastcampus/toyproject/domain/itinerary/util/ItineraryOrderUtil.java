package com.fastcampus.toyproject.domain.itinerary.util;

import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.DUPLICATE_ITINERARY_ORDER;
import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.INCORRECT_ITNERARY_ORDER;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.exception.ItineraryException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItineraryOrderUtil {

    /**
     * 여정 순서가 적절하게 입력되었는지 확인하는 메소드
     *
     * @param : itineraryList
     * @return
     */
    public static void validateItinerariesOrder(List<Integer> orderList) {
        //1. 순서가 중복되는지 검사하고, 순서대로 정렬. (O(NlogN))
        isItineraryOrdersNotDuplicated(orderList);
        isItineraryOrdersSorted(orderList);
    }


    public static void isItineraryOrdersNotDuplicated(List<Integer> orderList) {
        //1. 순서가 중복되는지 검사하고, 순서대로 정렬. (O(NlogN))
        Collections.sort(orderList, (o1, o2) -> {
            if (o1 == o2) {
                throw new ItineraryException(DUPLICATE_ITINERARY_ORDER);
            }
            return o1 - o2;
        });
    }

    private static void isItineraryOrdersSorted(List<Integer> orderList) {
        //2. 순서가 1부터 차례대로 들어갔는지 확인. (O(N))
        for (int orderIdx = 1; orderIdx <= orderList.size(); orderIdx++) {
            if (orderList.get(orderIdx - 1) != orderIdx) {
                throw new ItineraryException(INCORRECT_ITNERARY_ORDER);
            }
        }
    }

    /**
     * 테이블에 등록된 여정 순서대로 itinerary 리스트 정렬
     *
     * @param itResponseList
     */
    public static void sortItineraryResponseListByOrder(List<ItineraryResponse> itResponseList) {
        Collections.sort(itResponseList,
            Comparator.comparingInt(ItineraryResponse::getItineraryOrder));
    }


}
