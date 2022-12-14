package com.modsen.api.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventsFilterStrategy {
  THEME("theme"),
  DESCRIPTION("description"),
  ORGANIZER("organizer"),
  START("start"),
  PLACE("place");

  String filterParameter;

  public boolean isDateTimeStrategy() {
    return this == START;
  }
}
