package com.fastcampus.toyproject.domain.itinerary.dto;

import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.ILLEGAL_ARGUMENT_ARRIVALPLACE;
import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.ILLEGAL_ARGUMENT_DEPARTUREPLACE;

import com.fastcampus.toyproject.domain.itinerary.exception.ItineraryException;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryRequest {

    @NotNull
    private ItineraryType type;

    @NotNull
    private String item;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Min(1)
    private Integer order;

    private String departurePlace;
    private String arrivalPlace;

    public String getMovementName() {
        if (this.departurePlace == null) {
            throw new ItineraryException(ILLEGAL_ARGUMENT_DEPARTUREPLACE);
        }
        if (this.arrivalPlace == null) {
            throw new ItineraryException(ILLEGAL_ARGUMENT_ARRIVALPLACE);
        }
        return this.departurePlace + " -> " + this.arrivalPlace;
    }

}
