package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.common.util.LocationUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Stay;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StayResponse extends ItineraryResponse {

    private static LocationUtil locationUtil = new LocationUtil();

    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String timeDifference;
    private String location;

    public static StayResponse fromEntity(Stay entity) {
        return StayResponse
            .builder()
            .id(entity.getItineraryId())
            .itineraryName(entity.getItineraryName())
            .location(locationUtil.findLocation(entity.getItineraryName()))
            .itineraryOrder(entity.getItineraryOrder())
            .itineraryType(entity.getItineraryType())
            .departureDate(entity.getDepartureDate())
            .arrivalDate(entity.getArrivalDate())
            .timeDifference(
                DateUtil.getTimeBetweenDate(entity.getDepartureDate(), entity.getArrivalDate()))
            .build();
    }
}
