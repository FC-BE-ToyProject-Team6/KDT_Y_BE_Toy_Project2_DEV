package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryResponse {

    private Long id;
    private String itineraryName;
    private Integer itineraryOrder;
    private ItineraryType itineraryType;

    public static ItineraryResponse fromEntity(Itinerary itinerary) {
        return ItineraryResponse.builder()
                .id(itinerary.getItineraryId())
                .itineraryName(itinerary.getItineraryName())
                .itineraryOrder(itinerary.getItineraryOrder())
                .itineraryType(itinerary.getItineraryType())
                .build();
    }

}
