package com.fastcampus.toyproject.domain.itinerary.service;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.itinerary.dto.*;
import com.fastcampus.toyproject.domain.itinerary.entity.*;
import com.fastcampus.toyproject.domain.itinerary.repository.*;
import com.fastcampus.toyproject.domain.itinerary.util.ItineraryValidation;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
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

    private final MemberRepository memberRepository;

    @Transactional
    public void test() {
        // test 용 메소드 (멤버, 여행 일부러 삽입) -> 추후 삭제 예정
        Member member = Member.builder().memberId(1L).nickName("김종훈").build();
        memberRepository.save(member);
    }

    @Transactional
    public List<ItineraryResponse> insertItineraries (
            Long tripId,
            List<ItineraryRequest> request
    ) {
        //test();

        List<ItineraryResponse> itineraryResponses = new ArrayList<>();

        //0. tripid를 통한 trip 객체 찾기. (method : getTrip(tripId))
        Trip trip = getTrip(tripId);

        //1. 여정 테이블에서 모든 값 가져오기.
        List<Itinerary> itineraries = itineraryRepository
                .findAllByTripId(getTrip(tripId))
                .orElseGet(() -> new ArrayList<>());

        //2. 현 request 에서 entity로 변환하여 리스트에 추가.
        for (ItineraryRequest ir : request) {
            Itinerary itinerary = null;
            ItineraryResponse itineraryResponse = null;

            switch (ir.getType()) {
                case 1:
                    if (ir.getDeparturePlace() == null || ir.getArrivalPlace() == null) {
                        throw new RuntimeException();
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
                .orElseThrow(() -> new RuntimeException()
                );
    }
}
