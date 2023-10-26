package com.fastcampus.toyproject.domain.itinerary.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryResponse {

    private Long id;
    private String itineraryName;
    private Integer itineraryOrder;
    private String itineraryType;

    public static ItineraryResponse fromEntity(Itinerary itinerary) {
        return ItineraryResponse.builder()
                .id(itinerary.getItineraryId())
                .itineraryName(itinerary.getItineraryName())
                .itineraryOrder(itinerary.getItineraryOrder())
                .itineraryType(itinerary.getItineraryName())
                .build();
    }

}
