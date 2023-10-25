package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Movement;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse extends ItineraryResponse {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departurePlace;
    private String arrivalPlace;
    private String transportation;

    public static MovementResponse fromEntity(Movement entity) {
        return MovementResponse
                .builder()
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType("Movement")
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .departurePlace(entity.getDeparturePlace())
                .arrivalPlace(entity.getArrivalPlace())
                .transportation(entity.getTransportation())
                .build();
    }
}
