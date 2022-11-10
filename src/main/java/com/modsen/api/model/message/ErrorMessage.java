package com.modsen.api.model.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMessage {
  INVALID_API_USAGE(
      "Please visit https://github.com/IvanHayel/modsen-crud-api to see API features."),
  ILLEGAL_DATE_TIME_FORMAT("Illegal date/time format! Please use ISO 8601.");

  String message;
}
