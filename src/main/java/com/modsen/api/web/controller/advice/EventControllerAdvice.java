package com.modsen.api.web.controller.advice;

import com.modsen.api.exception.event.EventNotFoundException;
import com.modsen.api.model.message.AdviceErrorMessage;
import com.modsen.api.model.message.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class EventControllerAdvice {
  @ExceptionHandler(EventNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ServerResponse handleEventNotFoundException(
      WebRequest request, EventNotFoundException exception) {
    String description = request.getDescription(false);
    String message = exception.getMessage();
    return new AdviceErrorMessage(HttpStatus.BAD_REQUEST.value(), message, description);
  }
}
