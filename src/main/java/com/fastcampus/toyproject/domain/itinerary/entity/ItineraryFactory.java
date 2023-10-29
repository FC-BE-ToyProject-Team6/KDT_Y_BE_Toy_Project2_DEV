package com.fastcampus.toyproject.domain.itinerary.entity;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryRequest;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * request의 여정 타입이 들어오면 그에 따라 entity를 반환해주는 팩토리 패턴을 적용한 클래스
 */
public class ItineraryFactory {

    private final static Map<ItineraryType, BiFunction<Trip,
        ItineraryRequest, Itinerary>> map = new HashMap<>();

    static {
        map.put(ItineraryType.MOVEMENT, (trip, ir) ->
            Movement.builder()
                .trip(trip)
                .itineraryName(ir.getMovementName())
                .itineraryOrder(ir.getOrder())
                .itineraryType(ir.getType())
                .departureDate(ir.getStartDate())
                .arrivalDate(ir.getEndDate())
                .departurePlace(ir.getDeparturePlace())
                .arrivalPlace(ir.getArrivalPlace())
                .transportation(ir.getItem())
                .build()
        );

        map.put(ItineraryType.LODGEMENT, (trip, ir) ->
            Lodgement.builder()
                .trip(trip)
                .itineraryName(ir.getItem())
                .itineraryOrder(ir.getOrder())
                .itineraryType(ir.getType())
                .checkIn(ir.getStartDate())
                .checkOut(ir.getEndDate())
                .build()
        );

        map.put(ItineraryType.STAY, (trip, ir) ->
            Stay.builder()
                .trip(trip)
                .itineraryName(ir.getItem())
                .itineraryOrder(ir.getOrder())
                .itineraryType(ir.getType())
                .departureDate(ir.getStartDate())
                .arrivalDate(ir.getEndDate())
                .build()
        );
    }

    public static Itinerary getItineraryEntity(Trip trip, ItineraryRequest ir) {
        BiFunction<Trip, ItineraryRequest, Itinerary> function = map.get(ir.getType());
        return function.apply(trip, ir);
    }
}
