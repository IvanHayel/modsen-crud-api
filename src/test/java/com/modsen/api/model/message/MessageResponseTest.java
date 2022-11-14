package com.modsen.api.model.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessageResponseTest {
  private static final String TEST_MESSAGE = "Not all who wander are lost";

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link MessageResponse#MessageResponse(String)}
   *   <li>{@link MessageResponse#getMessage()}
   * </ul>
   */
  @Test
  void testAllArgsConstructor() {
    assertEquals(TEST_MESSAGE, (new MessageResponse(TEST_MESSAGE)).getMessage());
  }
}
