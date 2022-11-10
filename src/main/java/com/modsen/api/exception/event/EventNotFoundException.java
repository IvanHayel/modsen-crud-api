package com.modsen.api.exception.event;

public class EventNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Event not found!";
  private static final String MESSAGE_WITH_ID = "Event with id %d not found!";

  public EventNotFoundException() {
    super(DEFAULT_MESSAGE);
  }

  public EventNotFoundException(Long id) {
    super(String.format(MESSAGE_WITH_ID, id));
  }
}
