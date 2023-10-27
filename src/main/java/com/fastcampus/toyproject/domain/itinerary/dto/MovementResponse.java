package com.fastcampus.toyproject.domain.itinerary.dto;

import static com.fastcampus.toyproject.domain.itinerary.type.ItineraryType.MOVEMENT;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Movement;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
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
    private String timeDifference;

    public static MovementResponse fromEntity(Movement entity) {
        return MovementResponse
                .builder()
                .id(entity.getItineraryId())
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType(MOVEMENT.getValue())
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .departurePlace(entity.getDeparturePlace())
                .arrivalPlace(entity.getArrivalPlace())
                .transportation(entity.getTransportation())
                .timeDifference(DateUtil.getTimeBetweenDate(entity.getDepartureDate(), entity.getArrivalDate()))
                .build();
    }
}
