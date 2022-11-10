package com.modsen.api.web.controller;

import com.modsen.api.model.dto.EventDto;
import com.modsen.api.model.message.MessageResponse;
import com.modsen.api.model.message.ServerResponse;
import com.modsen.api.service.event.EventService;
import com.modsen.api.service.search.HibernateSearchService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventController {
  EventService eventService;
  HibernateSearchService searchService;

  @GetMapping
  public List<EventDto> getAllEvents(HttpServletRequest request) {
    var queryParameters = request.getParameterMap();
    return eventService.findAll(queryParameters);
  }

  @GetMapping("/search")
  public List<EventDto> searchEvents(@RequestParam String term) {
    return searchService.searchForEvents(term);
  }

  @GetMapping("/{id}")
  public EventDto getEventById(@PathVariable Long id) {
    return eventService.findById(id);
  }

  @PostMapping
  public EventDto addEvent(@RequestBody @Valid EventDto eventDto) {
    return eventService.save(eventDto);
  }

  @PutMapping
  public EventDto updateEvent(@RequestBody @Valid EventDto eventDto) {
    return eventService.update(eventDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ServerResponse> deleteEvent(@PathVariable Long id) {
    eventService.deleteById(id);
    return ResponseEntity.ok(MessageResponse.SUCCESS_DELETE_EVENT);
  }
}
