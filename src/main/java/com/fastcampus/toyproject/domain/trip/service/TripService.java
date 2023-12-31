package com.fastcampus.toyproject.domain.trip.service;


import static com.fastcampus.toyproject.domain.trip.exception.TripExceptionCode.NO_SUCH_TRIP;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.dto.TripDetailResponse;
import com.fastcampus.toyproject.domain.trip.dto.TripRequest;
import com.fastcampus.toyproject.domain.trip.dto.TripResponse;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.exception.TripException;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;
    private final ItineraryService itineraryService;

    private Member getValidatedMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(
                () -> new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 멤버가 없습니다."));
    }

    /**
     * trip 아이디를 통한 trip 객체 반환하는 메소드
     *
     * @param tripId
     * @return trip
     */
    public Trip getTripByTripId(Long tripId) {

        Trip trip = tripRepository
            .findById(tripId)
            .orElseThrow(() -> new TripException(NO_SUCH_TRIP));

        List<Itinerary> list = trip.getItineraryList();

        for(int i = 0 ; i < list.size() ; i++){
            if(list.get(i).getIsDeleted()){
                list.remove(i);
                i--;
            }
        }

        return trip;
    }

    /**
     * trip 객체로 연관된 itinerary들의 이름만 반환하는 메소드
     *
     * @param trip
     * @return string
     */
    public String getItineraryNamesByTrip(Trip trip) {
        return itineraryService.getItineraryResponseListByTrip(trip)
            .stream()
            .map(it -> it.getItineraryName())
            .collect(Collectors.joining(", "));
    }

    /**
     * 삭제 되지 않은 trip 전부를 반환하는 메소드
     *
     * @return List<TripResponseDTO>
     */
    @Transactional(readOnly = true)
    public List<TripResponse> getAllTrips() {
        return tripRepository.findAll()
            .stream().filter(trip -> !trip.getIsDeleted())
            .map(TripResponse::fromEntity).
            collect(Collectors.toList());
    }

    /**
     * trip과 연관된 itinerary 리스트 반환 (여행 상세 조회)
     *
     * @param tripId
     * @return tripDetailDTO
     */
    @Transactional(readOnly = true)
    public TripDetailResponse getTripDetail(Long tripId) {
        return TripDetailResponse.fromEntity(getTripByTripId(tripId));
    }

    /**
     * trip 1개 삽입하는 메소드
     *
     * @param memberId
     * @param tripRequest
     * @return tripResponseDTO
     */
    public TripResponse insertTrip(Long memberId, TripRequest tripRequest) {

        Trip trip = Trip.builder()
            .member(getValidatedMember(memberId))
            .tripName(tripRequest.getTripName())
            .startDate(tripRequest.getStartDate())
            .endDate(tripRequest.getEndDate())
            .isDomestic(tripRequest.getIsDomestic())
            .isDeleted(false)
            .build();

        return TripResponse.fromEntity(tripRepository.save(trip));
    }

    /**
     * trip 수정하는 메소드
     *
     * @param memberId
     * @param tripId
     * @param tripRequest
     * @return tripResponseDTO
     */
    public TripResponse updateTrip(Long memberId, Long tripId, TripRequest tripRequest) {
        Trip existTrip = getTripByTripId(tripId);

        if (!existTrip.getMember().getMemberId().equals(memberId)) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "멤버의 여행 정보가 일치하지 않습니다.");
        }

        existTrip.updateFromDTO(tripRequest);
        return TripResponse.fromEntity(tripRepository.save(existTrip));
    }

    /**
     * trip 삭제 및 연관된 itinerary 삭제하는 메소드
     *
     * @param tripId
     */
    public TripResponse deleteTrip(Long tripId) {
        Trip trip = getTripByTripId(tripId);
        trip.delete();
        itineraryService.deleteAllItineraryByTrip(trip);
        return TripResponse.fromEntity(tripRepository.save(trip));
    }
}
