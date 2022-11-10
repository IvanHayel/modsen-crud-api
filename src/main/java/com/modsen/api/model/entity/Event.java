package com.modsen.api.model.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

@Entity
@Indexed
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event extends BaseEntity {
  @FullTextField
  @Column(nullable = false)
  String theme;

  @FullTextField
  @Column(nullable = false)
  String description;

  @FullTextField
  @Column(nullable = false)
  String organizer;

  @FullTextField
  @Column(nullable = false)
  String place;

  @GenericField
  @Column(name = "start_date", nullable = false)
  ZonedDateTime start;
}
