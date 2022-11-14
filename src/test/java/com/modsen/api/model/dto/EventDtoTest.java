package com.modsen.api.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class EventDtoTest {
  private static final long TEST_ID = 123L;
  private static final String TEST_THEME = "Theme";
  private static final String TEST_DESCRIPTION = "The characteristics of someone or something";
  private static final String TEST_ORGANIZER = "Organizer";
  private static final String TEST_PLACE = "Place";
  private static final ZonedDateTime TEST_START = ZonedDateTime.now();

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link EventDto#EventDto(Long, String, String, String, String, ZonedDateTime)}
   *   <li>{@link EventDto#getDescription()}
   *   <li>{@link EventDto#getId()}
   *   <li>{@link EventDto#getOrganizer()}
   *   <li>{@link EventDto#getPlace()}
   *   <li>{@link EventDto#getStart()}
   *   <li>{@link EventDto#getTheme()}
   * </ul>
   */
  @Test
  void testAllArgsConstructor() {
    var actualEventDto =
        new EventDto(TEST_ID, TEST_THEME, TEST_DESCRIPTION, TEST_ORGANIZER, TEST_PLACE, TEST_START);
    assertNotNull(actualEventDto.getId());
    assertEquals(TEST_ID, actualEventDto.getId().longValue());
    assertEquals(TEST_THEME, actualEventDto.getTheme());
    assertEquals(TEST_DESCRIPTION, actualEventDto.getDescription());
    assertEquals(TEST_ORGANIZER, actualEventDto.getOrganizer());
    assertEquals(TEST_PLACE, actualEventDto.getPlace());
    assertEquals(TEST_START, actualEventDto.getStart());
  }
}
