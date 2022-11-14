package com.modsen.api.web.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.modsen.api.model.dto.EventDto;
import com.modsen.api.model.message.MessageResponse;
import com.modsen.api.service.event.EventService;
import com.modsen.api.service.search.HibernateSearchService;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EventController.class})
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class EventControllerTest {
  private static final List<EventDto> TEST_EVENTS;
  private static final EventDto TEST_EVENT;
  private static final Long TEST_ID = 123L;
  private static final String TEST_STRING = "TEST";
  private static final ZonedDateTime TEST_DATE_TIME =
      ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

  private static final String JSON_CONTENT_TYPE = "application/json";
  private static final String TEST_URL = "/api/v1/events";
  private static final String TEST_URL_WITH_ID = TEST_URL + "/{id}";
  private static final String TEST_URL_WITH_SEARCH = TEST_URL + "/search";
  private static final String SEARCH_REQUIRED_PARAMETER = "term";
  private static final String TEST_SEARCH_TERM = "foo";

  private static final ObjectMapper OBJECT_MAPPER;

  static {
    TEST_EVENT =
        new EventDto(TEST_ID, TEST_STRING, TEST_STRING, TEST_STRING, TEST_STRING, TEST_DATE_TIME);
    TEST_EVENTS = List.of(TEST_EVENT);

    OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
  }

  @Autowired EventController eventController;

  @MockBean EventService eventService;
  @MockBean HibernateSearchService hibernateSearchService;

  /** Method under test: {@link EventController#getAllEvents(HttpServletRequest)} */
  @Test
  void testGetAllEvents() throws Exception {
    when(eventService.findAll((any()))).thenReturn(TEST_EVENTS);
    var expectedJson = OBJECT_MAPPER.writeValueAsString(TEST_EVENTS);
    var requestBuilder = MockMvcRequestBuilders.get(TEST_URL);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

  /** Method under test: {@link EventController#searchEvents(String)} */
  @Test
  void testSearchEvents() throws Exception {
    when(hibernateSearchService.searchForEvents(any(String.class))).thenReturn(TEST_EVENTS);
    var expectedJson = OBJECT_MAPPER.writeValueAsString(TEST_EVENTS);
    var requestBuilder =
        MockMvcRequestBuilders.get(TEST_URL_WITH_SEARCH)
            .param(SEARCH_REQUIRED_PARAMETER, TEST_SEARCH_TERM);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

  /** Method under test: {@link EventController#getEventById(Long)} */
  @Test
  void testGetEventById() throws Exception {
    when(eventService.findById(any(Long.class))).thenReturn(TEST_EVENT);
    var expectedJson = OBJECT_MAPPER.writeValueAsString(TEST_EVENT);
    var requestBuilder = MockMvcRequestBuilders.get(TEST_URL_WITH_ID, TEST_ID);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

  /** Method under test: {@link EventController#addEvent(EventDto)} */
  @Test
  void testAddEvent() throws Exception {
    when(eventService.save(any(EventDto.class))).thenReturn(TEST_EVENT);
    var expectedJson = OBJECT_MAPPER.writeValueAsString(TEST_EVENT);
    var requestBuilder =
        MockMvcRequestBuilders.post(TEST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(expectedJson);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

  /** Method under test: {@link EventController#updateEvent(EventDto)} */
  @Test
  void testUpdateEvent() throws Exception {
    when(eventService.update(any(EventDto.class))).thenReturn(TEST_EVENT);
    var expectedJson = OBJECT_MAPPER.writeValueAsString(TEST_EVENT);
    var requestBuilder =
        MockMvcRequestBuilders.put(TEST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(expectedJson);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

  /** Method under test: {@link EventController#deleteEvent(Long)} */
  @Test
  void testDeleteEvent() throws Exception {
    doNothing().when(eventService).deleteById(any(Long.class));
    var expectedJson = OBJECT_MAPPER.writeValueAsString(MessageResponse.SUCCESS_DELETE_EVENT);
    var requestBuilder = MockMvcRequestBuilders.delete(TEST_URL_WITH_ID, TEST_ID);
    MockMvcBuilders.standaloneSetup(eventController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(JSON_CONTENT_TYPE))
        .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }
}
