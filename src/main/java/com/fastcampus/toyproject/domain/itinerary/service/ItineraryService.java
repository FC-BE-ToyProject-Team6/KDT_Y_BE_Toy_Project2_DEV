package com.fastcampus.toyproject.domain.itinerary.service;

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
    public List<ItineraryResponse> insertItineraries (
            Long tripId,
            List<ItineraryRequest> request
    ) {

        List<ItineraryResponse> itineraryResponses = new ArrayList<>();

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
                    if (ir.getDeparturePlace() == null) {
                        throw new RuntimeException("이동의 경우 출발지 입력 하셔야 합니다.");
                    }

                    if (ir.getArrivalPlace() == null) {
                        throw new RuntimeException("이동의 경우 도착지 입력 하셔야 합니다.");
                    }

                    itinerary = Movement
                            .builder()
                            .tripId(trip)
                            .itineraryName(ir.getName())
                            .itineraryOrder(ir.getOrder())
                            .departureDate(ir.getStartDate())
                            .arrivalDate(ir.getEndDate())
                            .departurePlace(ir.getDeparturePlace())
                            .arrivalPlace(ir.getArrivalPlace())
                            .build();
                    movementRepository.save((Movement) itinerary);
                    itineraryResponse = MovementResponse.fromEntity((Movement) itinerary);
                    break;
                case 2:
                    itinerary = Lodgement
                            .builder()
                            .tripId(trip)
                            .itineraryName(ir.getName())
                            .itineraryOrder(ir.getOrder())
                            .checkIn(ir.getStartDate())
                            .checkOut(ir.getEndDate())
                            .build();
                    lodgementRepository.save((Lodgement) itinerary);
                    itineraryResponse = LodgementResponse.fromEntity((Lodgement) itinerary);
                    break;
                case 3:
                    itinerary = Stay
                            .builder()
                            .tripId(trip)
                            .itineraryName(ir.getName())
                            .itineraryOrder(ir.getOrder())
                            .departureDate(ir.getStartDate())
                            .arrivalDate(ir.getEndDate())
                            .build();
                    stayRepository.save((Stay) itinerary);
                    itineraryResponse = StayResponse.fromEntity((Stay) itinerary);
                    break;
            }
            itineraries.add(itinerary);
            itineraryResponses.add(itineraryResponse);
        }

        //3. 검증
        ItineraryValidation.validateItinerariesOrder(itineraries);

        //4. response 리스트 정렬. (오더 순서대로)
        Collections.sort(itineraryResponses, Comparator.comparingInt(ItineraryResponse::getItineraryOrder));

        return itineraryResponses;

    }

    private Trip getTrip(Long tripId) {
        return tripRepository
                .findById(tripId)
                .orElseThrow(() -> new RuntimeException("해당되는 여행이 없습니다.")
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
                    ))
                    ;
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
            //리스트 순대로 order 재정의.
            Itinerary it = itineraries.get(order - 1);
            it.updateItineraryOrder(order);
            itineraryResponseList.add(ItineraryResponse.fromEntity(it));
        }

        return itineraryResponseList;
    }

    @Transactional(readOnly = false)
    public List<ItineraryResponse> updateItinerary(Long tripId,
        List<ItineraryUpdateRequest> itineraryUpdateRequests) {

        if (itineraryUpdateRequests == null || itineraryUpdateRequests.isEmpty()) {
            throw new DefaultException(ExceptionCode.EMPTY_ITINERARY);
        }

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();

        for (ItineraryUpdateRequest req : itineraryUpdateRequests) {

            switch (req.getType()) {
                case 1:
                    Movement movement = movementRepository.findById(req.getItineraryId())
                        .orElseThrow(
                            () -> new DefaultException(ExceptionCode.NO_ITINERARY));

                    movement.updateItineraryName(req.getItineraryName());
                    movement.updateItineraryOrder(req.getItineraryOrder());
                    movement.updateDepartureDate(req.getStartDate());
                    movement.updateDeparturePlace(req.getDeparturePlace());
                    movement.updateArrivalDate(req.getEndDate());
                    movement.updateArrivalPlace(req.getArrivalPlace());

                    itineraryResponseList.add(ItineraryResponse.fromEntity(movement));

                    movementRepository.flush();

                    break;
                case 2:
                    Lodgement lodgement = lodgementRepository.findById(req.getItineraryId())
                        .orElseThrow(
                            () -> new DefaultException(ExceptionCode.NO_ITINERARY));
                    lodgement.updateItineraryName(req.getItineraryName());
                    lodgement.updateItineraryOrder(req.getItineraryOrder());
                    lodgement.updateCheckIn(req.getStartDate());
                    lodgement.updateCheckOut(req.getEndDate());

                    itineraryResponseList.add(ItineraryResponse.fromEntity(lodgement));

                    lodgementRepository.flush();
                    break;
                case 3:
                    Stay stay = stayRepository.findById(req.getItineraryId()).orElseThrow(
                        () -> new DefaultException(ExceptionCode.NO_SUCH_TRIP));

                    stay.updateItineraryName(req.getItineraryName());
                    stay.updateItineraryOrder(req.getItineraryOrder());
                    stay.updateDepartureDate(req.getStartDate());
                    stay.updateArrivalDate(req.getEndDate());

                    itineraryResponseList.add(ItineraryResponse.fromEntity(stay));

                    stayRepository.flush();

                    break;
            }

        }

        List<Itinerary> itineraryList = itineraryRepository.findAllByTripIdOrderByItineraryOrder(
            getTrip(tripId));

        ItineraryValidation.validateItinerariesOrder(itineraryList);

        return itineraryResponseList;
    }

}
