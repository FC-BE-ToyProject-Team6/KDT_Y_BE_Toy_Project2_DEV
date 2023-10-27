package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.common.util.LocationUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Movement;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse extends ItineraryResponse {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departurePlace;
    private String arrivalPlace;
    private String transportation;

    private static LocationUtil locationUtil = new LocationUtil();

    public static MovementResponse fromEntity(Movement entity) {
        return MovementResponse
                .builder()
                .id(entity.getItineraryId())
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType("Movement")
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .departurePlace(locationUtil.findLocation(entity.getDeparturePlace()))
                .arrivalPlace(locationUtil.findLocation(entity.getArrivalPlace()))
                .transportation(entity.getTransportation())
                .build();
    }
}
