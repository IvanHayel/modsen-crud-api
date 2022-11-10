package com.modsen.api.service.event;

import com.modsen.api.model.dto.EventDto;
import java.util.List;
import java.util.Map;

public interface EventService {
  List<EventDto> findAll(Map<String, String[]> parameters);

  EventDto findById(Long id);

  EventDto save(EventDto eventDto);

  EventDto update(EventDto eventDto);

  void deleteById(Long id);

  boolean isExist(Long id);
}
