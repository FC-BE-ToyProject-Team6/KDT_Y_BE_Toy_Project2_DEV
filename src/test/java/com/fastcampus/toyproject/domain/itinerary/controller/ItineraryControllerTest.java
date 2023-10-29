package com.fastcampus.toyproject.domain.itinerary.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import com.fastcampus.toyproject.domain.itinerary.type.ItineraryType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class ItineraryControllerTest {


    @InjectMocks
    private ItineraryController itineraryController;

    @Mock
    private ItineraryService itineraryService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private List<ItineraryUpdateRequest> reqList;

    @BeforeEach
    private void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itineraryController).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        reqList = List.of(ItineraryUpdateRequest.builder()
            .itineraryId(2L)
            .type(ItineraryType.MOVEMENT)
            .item("로켓")
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .order(3)
            .departurePlace("지구")
            .arrivalPlace("화성")
            .build());
    }


    @Test
    public void insertItineraryTest() throws Exception {

        given(itineraryService.insertItineraries(
            anyLong(), any()
        )).willReturn(
            null
        );

        mockMvc.perform(post("/api/member/1/trip-itineraries/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqList)))
            .andDo(print())
            .andExpect(status().isOk());

    }

    @Test
    public void updateItineraryTest() throws Exception {

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

    @Test
    public void deleteItienraryTest() throws Exception {

        List<Long> deleteList = List.of(1L, 2L);

        given(itineraryService.deleteItineraries(anyLong(), any()))
            .willReturn(null);

        mockMvc.perform(put("/api/member/1/trip-itineraries/delete/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deleteList)))
            .andDo(print())
            .andExpect(status().isOk());

    }
}
