package com.modsen.api.service.search;

import com.modsen.api.model.dto.EventDto;
import java.util.List;

public interface HibernateSearchService {
  List<EventDto> searchForEvents(String searchTerm);
}
