package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.itinerary.entity.Lodgement;
import com.fastcampus.toyproject.domain.itinerary.entity.Movement;
import com.fastcampus.toyproject.domain.itinerary.entity.Stay;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 여정의 타입이 들어오면 그에 따라 response를 반환해주는 팩토리 패턴을 적용한 클래스
 */
public class ItineraryResponseFactory {

    private final static Map<ItineraryType, Function<Itinerary, ItineraryResponse>> map = new HashMap<>();

    static {
        map.put(ItineraryType.MOVEMENT, (it) -> MovementResponse.fromEntity((Movement) it));
        map.put(ItineraryType.LODGEMENT, (it) -> LodgementResponse.fromEntity((Lodgement) it));
        map.put(ItineraryType.STAY, (it) -> StayResponse.fromEntity((Stay) it));
    }

    public static ItineraryResponse getItineraryResponse(Itinerary it) {
        Function<Itinerary, ItineraryResponse> function = map.get(it.getItineraryType());
        return function.apply(it);
    }

}
