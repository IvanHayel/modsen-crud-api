package com.modsen.api.model.entity;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventsSortStrategy {
  DEFAULT("id"),
  THEME("theme"),
  ORGANIZER("organizer"),
  START("start"),
  PLACE("place");

  public static final String SORT_PARAMETER = "sortedBy";

  String fieldName;

  public static boolean isExist(String field) {
    return Arrays.stream(EventsSortStrategy.values())
        .anyMatch(s -> s.fieldName.equalsIgnoreCase(field));
  }
}
