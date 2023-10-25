package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Stay;
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
public class StayResponse extends ItineraryResponse {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    public static StayResponse fromEntity(Stay entity) {
        return StayResponse
                .builder()
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType("Stay")
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .build();
    }
}
