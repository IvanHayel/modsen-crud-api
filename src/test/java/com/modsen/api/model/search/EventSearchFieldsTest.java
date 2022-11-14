package com.modsen.api.model.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class EventSearchFieldsTest {
  private static final int TOTAL_VALUES = 4;
  private static final String TEST_SEARCH_NAME = "DESCRIPTION";
  private static final String TEST_SEARCH_FIELD_NAME = "description";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link EventSearchFields#valueOf(String)}
   *   <li>{@link EventSearchFields#getFieldName()}
   * </ul>
   */
  @Test
  void testValueOf() {
    assertEquals(
        TEST_SEARCH_FIELD_NAME, EventSearchFields.valueOf(TEST_SEARCH_NAME).getFieldName());
  }

  /** Method under test: {@link EventSearchFields#values()} */
  @Test
  void testValues() {
    var actualValuesResult = EventSearchFields.values();
    assertEquals(TOTAL_VALUES, actualValuesResult.length);
  }

  /** Method under test: {@link EventSearchFields#getAllNames()} */
  @Test
  void testGetAllNames() {
    var actualAllNames = EventSearchFields.getAllNames();
    assertEquals(TOTAL_VALUES, actualAllNames.length);
    assertTrue(Arrays.asList(actualAllNames).contains(TEST_SEARCH_FIELD_NAME));
  }
}
