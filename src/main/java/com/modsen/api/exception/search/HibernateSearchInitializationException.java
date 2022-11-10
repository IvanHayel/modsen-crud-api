package com.modsen.api.exception.search;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class HibernateSearchInitializationException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Unable to initialize hibernate search!";

  public HibernateSearchInitializationException() {
    super(DEFAULT_MESSAGE);
  }
}
