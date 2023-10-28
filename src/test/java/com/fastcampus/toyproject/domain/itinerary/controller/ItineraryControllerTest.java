package com.fastcampus.toyproject.domain.itinerary.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryRequest;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class ItineraryControllerTest {


    @InjectMocks
    private ItineraryController itineraryController;

    @Mock
    private ItineraryService itineraryService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    private void setUup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itineraryController).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    public void updateTripTest() throws Exception {

        ItineraryUpdateRequest req = ItineraryUpdateRequest.builder()
            .itineraryId(2L)
            .type(ItineraryType.MOVEMENT)
            .item("로켓")
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .order(3)
            .departurePlace("지구")
            .arrivalPlace("화성")
            .build();

        List<ItineraryUpdateRequest> reqList = List.of(req);

        given(itineraryService.updateItineraries(
            anyLong(), any()
        )).willReturn(
            null
        );

        mockMvc.perform(put("/api/member/1/trip-itineraries/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqList)))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
