package com.modsen.api.model.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErrorMessageTest {
  private static final int TOTAL_VALUES = 2;
  private static final String TEST_MESSAGE_NAME = "INVALID_API_USAGE";
  private static final String TEST_MESSAGE =
      "Please visit https://github.com/IvanHayel/modsen-crud-api to see API features.";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link ErrorMessage#valueOf(String)}
   *   <li>{@link ErrorMessage#getMessage()}
   * </ul>
   */
  @Test
  void testValueOf() {
    assertEquals(TEST_MESSAGE, ErrorMessage.valueOf(TEST_MESSAGE_NAME).getMessage());
  }

  /** Method under test: {@link ErrorMessage#values()} */
  @Test
  void testValues() {
    var actualValuesResult = ErrorMessage.values();
    assertEquals(TOTAL_VALUES, actualValuesResult.length);
  }
}
