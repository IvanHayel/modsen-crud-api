package com.modsen.api.model.search;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventSearchFields {
  THEME("theme"),
  DESCRIPTION("description"),
  ORGANIZER("organizer"),
  PLACE("place");

  String name;

  public static String[] getAllNames() {
    return Arrays.stream(EventSearchFields.values())
        .map(EventSearchFields::getName)
        .toArray(String[]::new);
  }
}
