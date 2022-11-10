package com.modsen.api.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventsSortDirection {
  DEFAULT("asc"),
  ASC("asc"),
  DESC("desc");

  public static final String DIRECTION_PARAMETER = "direction";

  String direction;
}
