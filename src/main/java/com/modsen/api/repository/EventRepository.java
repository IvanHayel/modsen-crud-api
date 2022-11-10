package com.modsen.api.repository;

import com.modsen.api.model.entity.Event;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventRepository {
  List<Event> findAll(Map<String, String[]> parameters);

  Optional<Event> findById(Long id);

  void save(Event event);

  void update(Event event);

  void delete(Event event);
}
