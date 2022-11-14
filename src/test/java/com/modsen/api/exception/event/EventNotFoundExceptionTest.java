package com.modsen.api.exception.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EventNotFoundExceptionTest {
  private static final Long TEST_ID = 123L;
  private static final String DEFAULT_MESSAGE = "Event not found!";
  private static final String MESSAGE_WITH_TEST_ID = "Event with id 123 not found!";
  private static final int SUPPRESSED_LENGTH = 0;

  /** Method under test: {@link EventNotFoundException#EventNotFoundException()} */
  @Test
  void testNoArgsConstructor() {
    var actualEventNotFoundException = new EventNotFoundException();
    assertNull(actualEventNotFoundException.getCause());
    assertEquals(SUPPRESSED_LENGTH, actualEventNotFoundException.getSuppressed().length);
    assertEquals(DEFAULT_MESSAGE, actualEventNotFoundException.getMessage());
    assertEquals(DEFAULT_MESSAGE, actualEventNotFoundException.getLocalizedMessage());
  }

  /** Method under test: {@link EventNotFoundException#EventNotFoundException(Long)} */
  @Test
  void testConstructorWithId() {
    var actualEventNotFoundException = new EventNotFoundException(TEST_ID);
    assertNull(actualEventNotFoundException.getCause());
    assertEquals(SUPPRESSED_LENGTH, actualEventNotFoundException.getSuppressed().length);
    assertEquals(MESSAGE_WITH_TEST_ID, actualEventNotFoundException.getMessage());
    assertEquals(MESSAGE_WITH_TEST_ID, actualEventNotFoundException.getLocalizedMessage());
  }
}
