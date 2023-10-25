package com.fastcampus.toyproject.domain.itinerary.service;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.NO_SUCH_TRIP;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.itinerary.dto.*;
import com.fastcampus.toyproject.domain.itinerary.entity.*;
import com.fastcampus.toyproject.domain.itinerary.repository.*;
import com.fastcampus.toyproject.domain.itinerary.util.ItineraryValidation;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final TripRepository tripRepository;
    private final ItineraryRepository itineraryRepository;
    private final LodgementRepository lodgementRepository;
    private final MovementRepository movementRepository;
    private final StayRepository stayRepository;

    @Transactional
    public List<ItineraryResponse> insertItineraries(
            Long tripId,
            List<ItineraryRequest> request
    ) {

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();

        //0. tripid를 통한 trip 객체 찾기. (method : getTrip(tripId))
        Trip trip = getTrip(tripId);

        //1. 여정 테이블에서 모든 값 가져오기.
        List<Itinerary> itineraries = itineraryRepository
                .findAllByTripIdAndIsDeletedNull(getTrip(tripId))
                .orElseGet(ArrayList::new);

        //2. 현 request 에서 entity로 변환하여 리스트에 추가.
        for (ItineraryRequest ir : request) {
            Itinerary itinerary = null;
            ItineraryResponse itineraryResponse = null;

            switch (ir.getType()) {
                case 1:
                    itinerary = movementRepository.save(
                            Movement.builder()
                                    .tripId(trip)
                                    .itineraryName(ir.getMovementName())
                                    .itineraryOrder(ir.getOrder())
                                    .departureDate(ir.getStartDate())
                                    .arrivalDate(ir.getEndDate())
                                    .departurePlace(ir.getDeparturePlace())
                                    .arrivalPlace(ir.getArrivalPlace())
                                    .transportation(ir.getName())
                                    .build()
                    );
                    itineraryResponse = MovementResponse.fromEntity((Movement) itinerary);
                    break;
                case 2:
                    itinerary = lodgementRepository.save(
                            Lodgement.builder()
                                    .tripId(trip)
                                    .itineraryName(ir.getName())
                                    .itineraryOrder(ir.getOrder())
                                    .checkIn(ir.getStartDate())
                                    .checkOut(ir.getEndDate())
                                    .build()
                    );
                    itineraryResponse = LodgementResponse.fromEntity((Lodgement) itinerary);
                    break;
                case 3:
                    itinerary = stayRepository.save(
                            Stay.builder()
                                    .tripId(trip)
                                    .itineraryName(ir.getName())
                                    .itineraryOrder(ir.getOrder())
                                    .departureDate(ir.getStartDate())
                                    .arrivalDate(ir.getEndDate())
                                    .build()
                    );
                    itineraryResponse = StayResponse.fromEntity((Stay) itinerary);
                    break;
            }
            itineraries.add(itinerary);
            itineraryResponseList.add(itineraryResponse);
        }

        //3. 검증
        ItineraryValidation.validateItinerariesOrder(itineraries);

        //4. response 리스트 정렬. (오더 순서대로)
        Collections.sort(itineraryResponseList,
                Comparator.comparingInt(ItineraryResponse::getItineraryOrder));

        return itineraryResponseList;

    }

    private Trip getTrip(Long tripId) {
        return tripRepository
                .findById(tripId)
                .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP)
                );
    }

    @Transactional
    public List<ItineraryResponse> deleteItineraries(
            Long tripId,
            List<Long> deleteIdList
    ) {

        //1. 여정들 가져오기 (id만 적힌것들) -> delete 처리
        for (Long id : deleteIdList) {
            Itinerary it = itineraryRepository
                    .findById(id)
                    .orElseThrow(() -> new DefaultException(
                            ExceptionCode.NO_ITINERARY
                    ));
            it.delete(LocalDateTime.now());
            it.updateDeleted();
        }

        //2. 남은 여정들의 순서 재정의
        List<Itinerary> itineraries = itineraryRepository
                .findAllByTripIdAndIsDeletedNull(getTrip(tripId))
                .orElseGet(ArrayList::new);

        Collections.sort(itineraries, Comparator.comparingInt(Itinerary::getItineraryOrder));

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();
        for (int order = 1; order <= itineraries.size(); order++) {
            //리스트 순대로 order update
            Itinerary it = itineraries.get(order - 1);
            it.updateItineraryOrder(order);
            itineraryResponseList.add(ItineraryResponse.fromEntity(it));
        }

        return itineraryResponseList;
    }
}
