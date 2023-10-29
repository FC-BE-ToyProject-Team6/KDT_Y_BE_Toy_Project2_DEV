package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.common.util.LocationUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Lodgement;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LodgementResponse extends ItineraryResponse {

    private static LocationUtil locationUtil = new LocationUtil();

    private String location;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String dayDifference;

    public static LodgementResponse fromEntity(Lodgement entity) {
        return LodgementResponse
            .builder()
            .id(entity.getItineraryId())
            .itineraryName(entity.getItineraryName())
            .location(locationUtil.findLocation(entity.getItineraryName()))
            .itineraryOrder(entity.getItineraryOrder())
            .itineraryType(entity.getItineraryType())
            .checkIn(entity.getCheckIn())
            .checkOut(entity.getCheckOut())
            .dayDifference(DateUtil.getDaysBetweenDate(entity.getCheckIn(), entity.getCheckOut()))
            .build();
    }
}
