package com.modsen.api.model.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EventDto implements Serializable {
  @Nullable Long id;
  @NotNull @NotBlank String theme;
  @NotNull @NotBlank String description;
  @NotNull @NotBlank String organizer;
  @NotNull @NotBlank String place;
  @NotNull ZonedDateTime start;
}
