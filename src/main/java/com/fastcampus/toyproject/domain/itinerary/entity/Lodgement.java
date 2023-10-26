package com.fastcampus.toyproject.domain.itinerary.entity;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Lodgement extends Itinerary {

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    public void updateLodgement(ItineraryUpdateRequest req){
        super.updateItineraryName(req.getItem());
        super.updateItineraryOrder(req.getOrder());
        this.checkIn = req.getStartDate();
        this.checkOut = req.getEndDate();
    }

}
