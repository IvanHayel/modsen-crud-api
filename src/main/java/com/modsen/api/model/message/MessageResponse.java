package com.modsen.api.model.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageResponse implements ServerResponse {
  public static final MessageResponse SUCCESS_DELETE_EVENT =
      new MessageResponse("Event deleted successfully!");

  @NonNull String message;
}
