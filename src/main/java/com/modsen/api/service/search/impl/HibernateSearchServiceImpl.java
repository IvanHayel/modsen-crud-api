package com.modsen.api.service.search.impl;

import com.modsen.api.exception.search.HibernateSearchInitializationException;
import com.modsen.api.mapper.EventMapper;
import com.modsen.api.model.dto.EventDto;
import com.modsen.api.model.entity.Event;
import com.modsen.api.model.search.EventSearchFields;
import com.modsen.api.service.search.HibernateSearchService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HibernateSearchServiceImpl implements HibernateSearchService {
  final SessionFactory sessionFactory;
  final EventMapper eventMapper;

  boolean isInitialized = false;

  public void initializeHibernateSearch() {
    try {
      var session = sessionFactory.getCurrentSession();
      var searchSession = Search.session(session);
      var indexer = searchSession.massIndexer(Event.class);
      indexer.startAndWait();
      isInitialized = true;
    } catch (InterruptedException e) {
      throw new HibernateSearchInitializationException();
    }
  }

  private <T> SearchResult<T> searchAll(Class<T> type, String searchTerm, String... fields) {
    if (!isInitialized) initializeHibernateSearch();
    var session = sessionFactory.getCurrentSession();
    var searchSession = Search.session(session);
    return searchSession
        .search(type)
        .where(field -> field.match().fields(fields).matching(searchTerm))
        .fetchAll();
  }

  @Override
  @Transactional
  public List<EventDto> searchForEvents(String searchTerm) {
    var searchResult = searchAll(Event.class, searchTerm, EventSearchFields.getAllNames());
    return eventMapper.eventsToEventDtos(searchResult.hits());
  }
}
