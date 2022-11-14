package com.modsen.api.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EventsSortStrategyTest {
  private static final int TOTAL_VALUES = 6;
  private static final String TEST_STRATEGY_NAME = "DESCRIPTION";
  private static final String TEST_STRATEGY_FIELD_NAME = "description";
  private static final String NOT_STRATEGY_NAME = "FIELD";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link EventsSortStrategy#valueOf(String)}
   *   <li>{@link EventsSortStrategy#getFieldName()}
   * </ul>
   */
  @Test
  void testValueOf() {
    assertEquals(
        TEST_STRATEGY_FIELD_NAME, EventsSortStrategy.valueOf(TEST_STRATEGY_NAME).getFieldName());
  }

  /** Method under test: {@link EventsSortStrategy#values()} */
  @Test
  void testValues() {
    var actualValuesResult = EventsSortStrategy.values();
    assertEquals(TOTAL_VALUES, actualValuesResult.length);
  }

  /** Method under test: {@link EventsSortStrategy#isExist(String)} */
  @Test
  void testIsExist() {
    assertFalse(EventsSortStrategy.isExist(NOT_STRATEGY_NAME));
    assertTrue(EventsSortStrategy.isExist(TEST_STRATEGY_NAME));
  }
}
