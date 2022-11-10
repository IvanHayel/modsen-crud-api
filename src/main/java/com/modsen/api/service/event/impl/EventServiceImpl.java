package com.modsen.api.service.event.impl;

import com.modsen.api.exception.event.EventNotFoundException;
import com.modsen.api.mapper.EventMapper;
import com.modsen.api.model.dto.EventDto;
import com.modsen.api.repository.EventRepository;
import com.modsen.api.service.event.EventService;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventServiceImpl implements EventService {
  EventRepository repository;
  EventMapper mapper;

  @Override
  @Transactional
  public List<EventDto> findAll(Map<String, String[]> parameters) {
    var events = repository.findAll(parameters);
    return mapper.eventsToEventDtos(events);
  }

  @Override
  @Transactional
  public EventDto findById(Long id) {
    var event = repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    return mapper.eventToEventDto(event);
  }

  @Override
  @Transactional
  public EventDto save(EventDto eventDto) {
    var event = mapper.eventDtoToEvent(eventDto);
    repository.save(event);
    return mapper.eventToEventDto(event);
  }

  @Override
  @Transactional
  public EventDto update(EventDto eventDto) {
    var id = eventDto.getId();
    if (isExist(id)) {
      var event = mapper.eventDtoToEvent(eventDto);
      repository.update(event);
      return mapper.eventToEventDto(event);
    }
    throw new EventNotFoundException(id);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    var event = repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    repository.delete(event);
  }

  @Override
  @Transactional
  public boolean isExist(Long id) {
    return repository.findById(id).isPresent();
  }
}
