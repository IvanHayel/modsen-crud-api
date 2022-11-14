package com.modsen.api.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class EventTest {
  private static final String TEST_THEME = "Theme";
  private static final String TEST_DESCRIPTION = "The characteristics of someone or something";
  private static final String TEST_ORGANIZER = "Organizer";
  private static final String TEST_PLACE = "Place";
  private static final ZonedDateTime TEST_START = ZonedDateTime.now();

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Event}
   *   <li>{@link Event#setDescription(String)}
   *   <li>{@link Event#setOrganizer(String)}
   *   <li>{@link Event#setPlace(String)}
   *   <li>{@link Event#setStart(ZonedDateTime)}
   *   <li>{@link Event#setTheme(String)}
   *   <li>{@link Event#getDescription()}
   *   <li>{@link Event#getOrganizer()}
   *   <li>{@link Event#getPlace()}
   *   <li>{@link Event#getStart()}
   *   <li>{@link Event#getTheme()}
   * </ul>
   */
  @Test
  void testEvent() {
    var actualEvent = new Event();
    actualEvent.setTheme(TEST_THEME);
    actualEvent.setDescription(TEST_DESCRIPTION);
    actualEvent.setOrganizer(TEST_ORGANIZER);
    actualEvent.setPlace(TEST_PLACE);
    actualEvent.setStart(TEST_START);
    assertEquals(TEST_THEME, actualEvent.getTheme());
    assertEquals(TEST_DESCRIPTION, actualEvent.getDescription());
    assertEquals(TEST_ORGANIZER, actualEvent.getOrganizer());
    assertEquals(TEST_PLACE, actualEvent.getPlace());
    assertEquals(TEST_START, actualEvent.getStart());
  }
}
