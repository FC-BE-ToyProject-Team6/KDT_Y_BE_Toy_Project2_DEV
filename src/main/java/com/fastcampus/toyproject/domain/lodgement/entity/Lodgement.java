package com.fastcampus.toyproject.domain.lodgement.entity;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lodgement extends Itinerary {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;






}
