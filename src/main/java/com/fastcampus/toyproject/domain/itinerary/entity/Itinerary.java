package com.fastcampus.toyproject.domain.itinerary.entity;

import static com.fastcampus.toyproject.domain.itinerary.exception.ItineraryExceptionCode.ILLEGAL_ITINERARY_TYPE;

import com.fastcampus.toyproject.common.BaseTimeEntity;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
import com.fastcampus.toyproject.domain.itinerary.exception.ItineraryException;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
public class Itinerary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itineraryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripId")
    private Trip trip;

    @Column(nullable = false)
    private String itineraryName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItineraryType itineraryType;

    @Column(nullable = false)
    private Integer itineraryOrder;

    @ColumnDefault("false")
    private Boolean isDeleted;

    public void delete() {
        super.delete(LocalDateTime.now());
        this.isDeleted = true;
    }

    public void updateItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public void updateItineraryOrder(Integer newOrder) {
        this.itineraryOrder = newOrder;
    }

    public void update(ItineraryUpdateRequest req) {
        if (this instanceof Movement) {
            ((Movement) this).updateMovement(req);
        } else if (this instanceof Lodgement) {
            ((Lodgement) this).updateLodgement(req);
        } else if (this instanceof Stay) {
            ((Stay) this).updateStay(req);
        } else {
            throw new ItineraryException(ILLEGAL_ITINERARY_TYPE);
        }
    }
}
