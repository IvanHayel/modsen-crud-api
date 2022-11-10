package com.modsen.api.repository.impl;

import com.modsen.api.model.entity.Event;
import com.modsen.api.model.entity.EventsFilterStrategy;
import com.modsen.api.model.entity.EventsSortDirection;
import com.modsen.api.model.entity.EventsSortStrategy;
import com.modsen.api.repository.EventRepository;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventRepositoryImpl implements EventRepository {
  SessionFactory sessionFactory;

  @Override
  public List<Event> findAll(Map<String, String[]> parameters) {
    var session = sessionFactory.getCurrentSession();
    var builder = session.getCriteriaBuilder();
    var criteria = builder.createQuery(Event.class);
    var root = criteria.from(Event.class);
    resolveSortStrategy(parameters, builder, criteria, root);
    resolveFilterStrategy(parameters, builder, criteria, root);
    return session.createQuery(criteria).getResultList();
  }

  private void resolveSortStrategy(
      Map<String, String[]> parameters,
      CriteriaBuilder builder,
      CriteriaQuery<Event> criteria,
      Root<Event> root) {
    if (parameters.containsKey(EventsSortStrategy.SORT_PARAMETER)) {
      var sortParameter = parameters.get(EventsSortStrategy.SORT_PARAMETER)[0];
      if (EventsSortStrategy.isExist(sortParameter)) {
        var direction =
            parameters.get(EventsSortDirection.DIRECTION_PARAMETER) == null
                ? EventsSortDirection.DEFAULT.getDirection()
                : parameters.get(EventsSortDirection.DIRECTION_PARAMETER)[0];
        if (direction.equalsIgnoreCase(EventsSortDirection.DESC.getDirection())) {
          criteria.orderBy(builder.desc(root.get(sortParameter)));
        } else {
          criteria.orderBy(builder.asc(root.get(sortParameter)));
        }
      }
    }
  }

  private void resolveFilterStrategy(
      Map<String, String[]> parameters,
      CriteriaBuilder builder,
      CriteriaQuery<Event> criteria,
      Root<Event> root) {
    var predicates = new ArrayList<Predicate>();
    Arrays.stream(EventsFilterStrategy.values())
        .forEach(
            strategy -> {
              var parameter = strategy.getFilterParameter();
              if (parameters.containsKey(parameter)) {
                var value = parameters.get(parameter)[0];
                if (strategy.equals(EventsFilterStrategy.START)) {
                  predicates.add(builder.equal(root.get(parameter), ZonedDateTime.parse(value)));
                } else {
                  predicates.add(builder.equal(root.get(parameter), value));
                }
              }
            });
    criteria.where(predicates.toArray(Predicate[]::new));
  }

  @Override
  public Optional<Event> findById(Long id) {
    var session = sessionFactory.getCurrentSession();
    return session.byId(Event.class).loadOptional(id);
  }

  @Override
  public void save(Event event) {
    var session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(event);
  }

  @Override
  public void update(Event event) {
    var session = sessionFactory.getCurrentSession();
    session.merge(event);
  }

  @Override
  public void delete(Event event) {
    var session = sessionFactory.getCurrentSession();
    session.delete(event);
  }
}
