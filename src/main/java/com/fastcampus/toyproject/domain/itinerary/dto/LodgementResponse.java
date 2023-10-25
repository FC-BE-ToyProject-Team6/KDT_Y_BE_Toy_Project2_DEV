package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Lodgement;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LodgementResponse extends ItineraryResponse{
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public static LodgementResponse fromEntity(Lodgement entity) {
        return LodgementResponse
                .builder()
                .id(entity.getItineraryId())
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType("Lodgement")
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .build();
    }
}
