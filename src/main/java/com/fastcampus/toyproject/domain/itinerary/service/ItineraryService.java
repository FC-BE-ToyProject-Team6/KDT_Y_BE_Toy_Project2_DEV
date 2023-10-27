package com.fastcampus.toyproject.domain.itinerary.service;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.NO_SUCH_TRIP;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.dto.*;
import com.fastcampus.toyproject.domain.itinerary.entity.*;
import com.fastcampus.toyproject.domain.itinerary.repository.*;
import com.fastcampus.toyproject.domain.itinerary.util.ItineraryValidation;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final TripRepository tripRepository;
    private final ItineraryRepository itineraryRepository;
    private final LodgementRepository lodgementRepository;
    private final MovementRepository movementRepository;
    private final StayRepository stayRepository;

    /**
     * itinerary (1개 이상) 삽입하는 메소드
     * @param tripId
     * @param itineraryRequests
     * @return
     */
    @Transactional
    public List<ItineraryResponse> insertItineraries(
            Long tripId,
            List<ItineraryRequest> itineraryRequests
    ) {

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();

        //0. tripid를 통한 trip 객체 찾기. (method : getTrip(tripId))
        Trip trip = getTrip(tripId);

        //1. 여정 테이블에서 모든 값 가져오기.
        List<Itinerary> itineraries = getItineraryList(trip);

        //2. 현 request 에서 entity로 변환하여 리스트에 추가.
        for (ItineraryRequest ir : itineraryRequests) {
            Itinerary itinerary = null;
            ItineraryResponse itineraryResponse = null;

            switch (ir.getType()) {
                case MOVEMENT:
                    itinerary = movementRepository.save(
                            Movement.builder()
                                    .trip(trip)
                                    .itineraryName(ir.getMovementName())
                                    .itineraryOrder(ir.getOrder())
                                    .departureDate(ir.getStartDate())
                                    .arrivalDate(ir.getEndDate())
                                    .departurePlace(ir.getDeparturePlace())
                                    .arrivalPlace(ir.getArrivalPlace())
                                    .transportation(ir.getItem())
                                    .build()
                    );
                    itineraryResponse = MovementResponse.fromEntity((Movement) itinerary);
                    break;
                case LODGEMENT:
                    itinerary = lodgementRepository.save(
                            Lodgement.builder()
                                    .trip(trip)
                                    .itineraryName(ir.getItem())
                                    .itineraryOrder(ir.getOrder())
                                    .checkIn(ir.getStartDate())
                                    .checkOut(ir.getEndDate())
                                    .build()
                    );
                    itineraryResponse = LodgementResponse.fromEntity((Lodgement) itinerary);
                    break;
                case STAY:
                    itinerary = stayRepository.save(
                            Stay.builder()
                                    .trip(trip)
                                    .itineraryName(ir.getItem())
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
        sortItineraryResponseListByOrder(itineraryResponseList);

        return itineraryResponseList;
    }

    /**
     * trip 객체로 연관된 itinerary 리스트를 정렬된 여정 응답 리스트로 변환하여 반환하는 메소드
     * @param trip
     * @return
     */
    public List<ItineraryResponse> getItineraryListByTrip(Trip trip) {
        List<Itinerary> itineraryList = getItineraryList(trip);

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();
        for (Itinerary it : itineraryList) {
            if (it instanceof Movement) {
                itineraryResponseList.add(MovementResponse.fromEntity((Movement) it));
            } else if (it instanceof Lodgement) {
                itineraryResponseList.add(LodgementResponse.fromEntity((Lodgement) it));
            } else if (it instanceof Stay) {
                itineraryResponseList.add(StayResponse.fromEntity((Stay) it));
            }
        }

        sortItineraryResponseListByOrder(itineraryResponseList);
        return itineraryResponseList;
    }

    /**
     * trip 객체를 이용하여 연관된 itinerary 리스트 반환하는 메소드
     * @param trip
     * @return List<Itinerary>
     */
    private static List<Itinerary> getItineraryList(Trip trip) {
        List<Itinerary> itineraryList = trip.getItineraryList()
                .stream().filter(it -> it.getIsDeleted() == null)
                .collect(Collectors.toList());
        if (itineraryList == null) itineraryList = new ArrayList<>();
        return itineraryList;
    }

    /**
     * 테이블에 등록된 여정 순서대로 itinerary 리스트 정렬
     * @param itineraryResponseList
     */
    private static void sortItineraryResponseListByOrder(List<ItineraryResponse> itineraryResponseList) {
        Collections.sort(itineraryResponseList,
                Comparator.comparingInt(ItineraryResponse::getItineraryOrder));
    }

    /**
     * tripService 를 이용해 trip 객체 반환하는 메소드
     * @param tripId
     * @return
     */
    private Trip getTrip(Long tripId) {
        return tripRepository
                .findById(tripId)
                .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP));
    }

    /**
     * 여정 아이디 리스트 가져와 itinerary 들 삭제하는 메소드
     * @param tripId
     * @param deleteIdList
     * @return
     */
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
            it.delete();
        }

        //2. 남은 여정들의 순서 재정의
        List<Itinerary> itineraries = itineraryRepository
                .findAllByTripAndIsDeletedNull(getTrip(tripId))
                .orElseGet(ArrayList::new);

        Collections.sort(itineraries, Comparator.comparingInt(Itinerary::getItineraryOrder));

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();
        sortAgainItineraryOrder(itineraries, itineraryResponseList);

        return itineraryResponseList;
    }

    /**
     * itinerary 리스트 순서 재정의 및 여정 response List에 담아 반환하는 메소드
     * @param itineraries
     * @param itineraryResponseList
     */
    private static void sortAgainItineraryOrder(List<Itinerary> itineraries,
            List<ItineraryResponse> itineraryResponseList
    ) {
        for (int order = 1; order <= itineraries.size(); order++) {
            //리스트 순대로 order update
            Itinerary it = itineraries.get(order - 1);
            it.updateItineraryOrder(order);
            itineraryResponseList.add(ItineraryResponse.fromEntity(it));
        }
    }

    /**
     * trip 객체에 연관된 itinerary 들 전부 삭제 처리하는 메소드
     * @param trip
     */
    @Transactional
    public void deleteAllItineraryByTrip(Trip trip) {
        for (Itinerary it : getItineraryList(trip)) {
            it.delete();
        }
    }

    /**
     * itinerary (1개 이상) 수정하는 메소드
     * @param tripId
     * @param itineraryUpdateRequests
     * @return
     */
    @Transactional(readOnly = false)
    public List<ItineraryResponse> updateItineraries(Long tripId,
        List<ItineraryUpdateRequest> itineraryUpdateRequests) {

        if (itineraryUpdateRequests == null || itineraryUpdateRequests.isEmpty()) {
            throw new DefaultException(ExceptionCode.EMPTY_ITINERARY);
        }

        List<ItineraryResponse> itineraryResponseList = new ArrayList<>();

        for (ItineraryUpdateRequest req : itineraryUpdateRequests) {

            switch (req.getType()) {
                case MOVEMENT:
                    Movement movement = movementRepository.findById(req.getItineraryId())
                        .orElseThrow(
                            () -> new DefaultException(ExceptionCode.NO_ITINERARY));

                    movement.updateMovement(req);
                    itineraryResponseList.add(ItineraryResponse.fromEntity(movement));

                    break;
                case LODGEMENT:
                    Lodgement lodgement = lodgementRepository.findById(req.getItineraryId())
                        .orElseThrow(
                            () -> new DefaultException(ExceptionCode.NO_ITINERARY));

                    lodgement.updateLodgement(req);
                    itineraryResponseList.add(ItineraryResponse.fromEntity(lodgement));

                    break;
                case STAY:
                    Stay stay = stayRepository.findById(req.getItineraryId()).orElseThrow(
                        () -> new DefaultException(ExceptionCode.NO_SUCH_TRIP));

                    stay.updateStay(req);
                    itineraryResponseList.add(ItineraryResponse.fromEntity(stay));

//                    stayRepository.flush();

                    break;
            }

        }

        List<Itinerary> itineraryList = itineraryRepository.findAllByTripOrderByItineraryOrder(
            getTrip(tripId));

        ItineraryValidation.validateItinerariesOrder(itineraryList);
        sortItineraryResponseListByOrder(itineraryResponseList);

        return itineraryResponseList;
    }

}
