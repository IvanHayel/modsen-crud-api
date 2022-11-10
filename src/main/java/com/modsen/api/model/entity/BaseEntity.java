package com.modsen.api.model.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @GenericField
  @CreationTimestamp
  @Column(name = "created_at", updatable = false, nullable = false)
  ZonedDateTime createDate;

  @GenericField
  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  ZonedDateTime updateDate;
}
