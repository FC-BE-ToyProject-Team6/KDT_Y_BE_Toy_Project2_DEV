package com.fastcampus.toyproject.domain.itinerary.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Stay extends Itinerary {

    @Column(nullable = false)
    @Setter
    private LocalDateTime departureDate;

    @Column(nullable = false)
    @Setter
    private LocalDateTime arrivalDate;

}
