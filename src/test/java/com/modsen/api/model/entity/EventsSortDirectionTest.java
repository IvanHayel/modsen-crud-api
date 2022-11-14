package com.modsen.api.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventsSortDirectionTest {
  private static final int TOTAL_VALUES = 3;
  private static final String TEST_DIRECTION_NAME = "DEFAULT";
  private static final String TEST_DIRECTION = "asc";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link EventsSortDirection#valueOf(String)}
   *   <li>{@link EventsSortDirection#getDirection()}
   * </ul>
   */
  @Test
  void testValueOf() {
    assertEquals(TEST_DIRECTION, EventsSortDirection.valueOf(TEST_DIRECTION_NAME).getDirection());
  }

  /** Method under test: {@link EventsSortDirection#values()} */
  @Test
  void testValues() {
    var actualValuesResult = EventsSortDirection.values();
    assertEquals(TOTAL_VALUES, actualValuesResult.length);
  }
}
