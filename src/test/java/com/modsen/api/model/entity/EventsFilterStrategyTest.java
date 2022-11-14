package com.modsen.api.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EventsFilterStrategyTest {
  private static final int TOTAL_VALUES = 5;
  private static final String TEST_STRATEGY_NAME = "THEME";
  private static final String TEST_STRATEGY_PARAMETER = "theme";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link EventsFilterStrategy#valueOf(String)}
   *   <li>{@link EventsFilterStrategy#getFilterParameter()}
   * </ul>
   */
  @Test
  void testValueOf() {
    assertEquals(
        TEST_STRATEGY_PARAMETER,
        EventsFilterStrategy.valueOf(TEST_STRATEGY_NAME).getFilterParameter());
  }

  /** Method under test: {@link EventsFilterStrategy#values()} */
  @Test
  void testValues() {
    var actualValuesResult = EventsFilterStrategy.values();

    assertEquals(TOTAL_VALUES, actualValuesResult.length);
  }

  /** Method under test: {@link EventsFilterStrategy#isDateTimeStrategy()} */
  @Test
  void testIsDateTimeStrategy() {
    assertFalse(EventsFilterStrategy.THEME.isDateTimeStrategy());
    assertFalse(EventsFilterStrategy.ORGANIZER.isDateTimeStrategy());
    assertTrue(EventsFilterStrategy.START.isDateTimeStrategy());
  }
}
