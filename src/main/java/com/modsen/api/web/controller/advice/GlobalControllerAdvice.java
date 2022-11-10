package com.modsen.api.web.controller.advice;

import com.modsen.api.model.message.AdviceErrorMessage;
import com.modsen.api.model.message.ErrorMessage;
import com.modsen.api.model.message.ServerResponse;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(DateTimeParseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ServerResponse handleDateTimeParseException(WebRequest request) {
    String description = request.getDescription(false);
    String message = ErrorMessage.ILLEGAL_DATE_TIME_FORMAT.getMessage();
    return new AdviceErrorMessage(HttpStatus.BAD_REQUEST.value(), message, description);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ServerResponse handleMethodArgumentNotValidException(WebRequest request) {
    String description = request.getDescription(false);
    String message = ErrorMessage.INVALID_API_USAGE.getMessage();
    return new AdviceErrorMessage(HttpStatus.BAD_REQUEST.value(), message, description);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ServerResponse handleMissingServletRequestParameterException(
      WebRequest request, MissingServletRequestParameterException exception) {
    String description = request.getDescription(false);
    String message = exception.getMessage();
    return new AdviceErrorMessage(HttpStatus.BAD_REQUEST.value(), message, description);
  }
}
