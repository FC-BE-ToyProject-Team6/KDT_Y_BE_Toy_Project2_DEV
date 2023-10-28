package com.fastcampus.toyproject.domain.itinerary.dto;

import static com.fastcampus.toyproject.domain.itinerary.type.ItineraryType.LODGEMENT;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Lodgement;
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
public class LodgementResponse extends ItineraryResponse{
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String dayDifference;

    public static LodgementResponse fromEntity(Lodgement entity) {
        return LodgementResponse
                .builder()
                .id(entity.getItineraryId())
                .itineraryName(entity.getItineraryName())
                .itineraryOrder(entity.getItineraryOrder())
                .itineraryType(entity.getItineraryType())
                .checkIn(entity.getCheckIn())
                .checkOut(entity.getCheckOut())
                .dayDifference(DateUtil.getDaysBetweenDate(entity.getCheckIn(), entity.getCheckOut()))
                .build();
    }
}
