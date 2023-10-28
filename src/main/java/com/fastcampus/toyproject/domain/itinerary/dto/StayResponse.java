package com.fastcampus.toyproject.domain.itinerary.dto;

import static com.fastcampus.toyproject.domain.itinerary.type.ItineraryType.STAY;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Stay;
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
public class StayResponse extends ItineraryResponse {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String timeDifference;

    public static StayResponse fromEntity(Stay entity) {
        return StayResponse
                .builder()
                .id(entity.getItineraryId())
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType(entity.getItineraryType())
                .departureDate(entity.getDepartureDate())
                .arrivalDate(entity.getArrivalDate())
                .timeDifference(DateUtil.getTimeBetweenDate(entity.getDepartureDate(), entity.getArrivalDate()))
                .build();
    }
}
