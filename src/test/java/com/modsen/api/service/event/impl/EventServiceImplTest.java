package com.modsen.api.service.event.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.modsen.api.exception.event.EventNotFoundException;
import com.modsen.api.mapper.EventMapper;
import com.modsen.api.model.dto.EventDto;
import com.modsen.api.model.entity.Event;
import com.modsen.api.repository.EventRepository;
import com.modsen.api.service.event.EventService;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventServiceImpl.class})
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class EventServiceImplTest {
  private static final List<Event> TEST_EVENTS;
  private static final Event TEST_EVENT;
  private static final List<EventDto> TEST_EVENT_DTOS;
  private static final EventDto TEST_EVENT_DTO;
  private static final Long TEST_ID = 123L;
  private static final String TEST_STRING = "TEST";
  private static final ZonedDateTime TEST_DATE_TIME =
      ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
  private static final Map<String, String[]> TEST_PARAMETERS = new HashMap<>();

  static {
    TEST_EVENT = new Event();
    TEST_EVENT.setId(TEST_ID);
    TEST_EVENT.setTheme(TEST_STRING);
    TEST_EVENT.setDescription(TEST_STRING);
    TEST_EVENT.setOrganizer(TEST_STRING);
    TEST_EVENT.setPlace(TEST_STRING);
    TEST_EVENT.setStart(TEST_DATE_TIME);
    TEST_EVENTS = List.of(TEST_EVENT);

    TEST_EVENT_DTO =
        new EventDto(TEST_ID, TEST_STRING, TEST_STRING, TEST_STRING, TEST_STRING, TEST_DATE_TIME);
    TEST_EVENT_DTOS = List.of(TEST_EVENT_DTO);
  }

  @Autowired EventService eventService;

  @MockBean EventMapper eventMapper;
  @MockBean EventRepository eventRepository;

  /** Method under test: {@link EventServiceImpl#findAll(Map)} */
  @Test
  void testFindAll() {
    when(eventRepository.findAll(any())).thenReturn(TEST_EVENTS);
    when(eventMapper.eventsToEventDtos(any())).thenReturn(TEST_EVENT_DTOS);
    var actualFindAllResult = eventService.findAll(TEST_PARAMETERS);
    assertSame(TEST_EVENT_DTOS, actualFindAllResult);
    assertFalse(actualFindAllResult.isEmpty());
    verify(eventRepository).findAll(any());
    verify(eventMapper).eventsToEventDtos(any());
  }

  /** Method under test: {@link EventServiceImpl#findById(Long)} */
  @Test
  void testFindById() {
    var eventOptional = Optional.of(TEST_EVENT);
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    when(eventMapper.eventToEventDto(any(Event.class))).thenReturn(TEST_EVENT_DTO);
    assertSame(TEST_EVENT_DTO, eventService.findById(TEST_ID));
    verify(eventRepository).findById(any(Long.class));
    verify(eventMapper).eventToEventDto(any(Event.class));
  }

  /** Method under test: {@link EventServiceImpl#findById(Long)} */
  @Test
  void testFindByIdNotFound() {
    var eventOptional = Optional.of(TEST_EVENT);
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    when(eventMapper.eventToEventDto(any(Event.class))).thenThrow(new EventNotFoundException());
    assertThrows(EventNotFoundException.class, () -> eventService.findById(TEST_ID));
    verify(eventRepository).findById(any(Long.class));
    verify(eventMapper).eventToEventDto(any(Event.class));
  }

  /** Method under test: {@link EventServiceImpl#save(EventDto)} */
  @Test
  void testSave() {
    doNothing().when(eventRepository).save(any(Event.class));
    when(eventMapper.eventToEventDto(any(Event.class))).thenReturn(TEST_EVENT_DTO);
    when(eventMapper.eventDtoToEvent(any(EventDto.class))).thenReturn(TEST_EVENT);
    assertSame(TEST_EVENT_DTO, eventService.save(new EventDto()));
    verify(eventRepository).save(any(Event.class));
    verify(eventMapper).eventToEventDto(any(Event.class));
    verify(eventMapper).eventDtoToEvent(any(EventDto.class));
  }

  /** Method under test: {@link EventServiceImpl#update(EventDto)} */
  @Test
  void testUpdate() {
    var eventOptional = Optional.of(TEST_EVENT);
    doNothing().when(eventRepository).update(any(Event.class));
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    when(eventMapper.eventToEventDto(any(Event.class))).thenReturn(TEST_EVENT_DTO);
    when(eventMapper.eventDtoToEvent(any(EventDto.class))).thenReturn(TEST_EVENT);
    assertSame(TEST_EVENT_DTO, eventService.update(TEST_EVENT_DTO));
    verify(eventRepository).findById(any(Long.class));
    verify(eventRepository).update(any(Event.class));
    verify(eventMapper).eventToEventDto(any(Event.class));
    verify(eventMapper).eventDtoToEvent(any(EventDto.class));
  }

  /** Method under test: {@link EventServiceImpl#update(EventDto)} */
  @Test
  void testUpdateNotFound() {
    assertThrows(EventNotFoundException.class, () -> eventService.update(TEST_EVENT_DTO));
  }

  /** Method under test: {@link EventServiceImpl#deleteById(Long)} */
  @Test
  void testDeleteById() {
    var eventOptional = Optional.of(TEST_EVENT);
    doNothing().when(eventRepository).delete(any(Event.class));
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    eventService.deleteById(TEST_ID);
    verify(eventRepository).findById(any(Long.class));
    verify(eventRepository).delete(any(Event.class));
  }

  /** Method under test: {@link EventServiceImpl#deleteById(Long)} */
  @Test
  void testDeleteByIdNotFound() {
    var eventOptional = Optional.of(TEST_EVENT);
    doThrow(new EventNotFoundException()).when(eventRepository).delete(any(Event.class));
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    assertThrows(EventNotFoundException.class, () -> eventService.deleteById(TEST_ID));
    verify(eventRepository).findById(any(Long.class));
    verify(eventRepository).delete(any(Event.class));
  }

  /** Method under test: {@link EventServiceImpl#isExist(Long)} */
  @Test
  void testIsExist() {
    var eventOptional = Optional.of(TEST_EVENT);
    when(eventRepository.findById(any(Long.class))).thenReturn(eventOptional);
    assertTrue(eventService.isExist(TEST_ID));
    verify(eventRepository).findById(any(Long.class));
  }

  /** Method under test: {@link EventServiceImpl#isExist(Long)} */
  @Test
  void testIsExistNotFound() {
    when(eventRepository.findById(any(Long.class))).thenReturn(Optional.empty());
    assertFalse(eventService.isExist(TEST_ID));
    verify(eventRepository).findById(any(Long.class));
  }
}
