package com.modsen.api.mapper;

import com.modsen.api.model.dto.EventDto;
import com.modsen.api.model.entity.Event;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {
  @Mapping(target = "updateDate", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  Event eventDtoToEvent(EventDto eventDto);

  EventDto eventToEventDto(Event event);

  List<EventDto> eventsToEventDtos(List<Event> events);
}
