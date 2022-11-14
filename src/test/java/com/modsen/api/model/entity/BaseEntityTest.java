package com.modsen.api.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseEntityTest {
  private static final BaseEntity TEST_ENTITY = new BaseEntity() {};
  private static final BaseEntity TEST_EMPTY_ENTITY = new BaseEntity() {};

  private static final Long TEST_ID = 123L;
  private static final ZonedDateTime TEST_DATE_TIME =
      ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());

  private static final Random GENERATOR = new Random();

  @BeforeEach
  void setUp() {
    TEST_ENTITY.setId(TEST_ID);
    TEST_ENTITY.setCreateDate(TEST_DATE_TIME);
    TEST_ENTITY.setUpdateDate(TEST_DATE_TIME);
  }

  /** Method under test: {@link BaseEntity#getId()} */
  @Test
  void testGetId() {
    assertNull(TEST_EMPTY_ENTITY.getId());
    assertEquals(TEST_ID, TEST_ENTITY.getId());
  }

  /** Method under test: {@link BaseEntity#getCreateDate()} */
  @Test
  void testGetCreateDate() {
    assertNull(TEST_EMPTY_ENTITY.getCreateDate());
    assertEquals(TEST_DATE_TIME, TEST_ENTITY.getCreateDate());
  }

  /** Method under test: {@link BaseEntity#getUpdateDate()} */
  @Test
  void testGetUpdateDate() {
    assertNull(TEST_EMPTY_ENTITY.getCreateDate());
    assertEquals(TEST_DATE_TIME, TEST_ENTITY.getCreateDate());
  }

  /** Method under test: {@link BaseEntity#setId(Long)} */
  @Test
  void testSetId() {
    long id = GENERATOR.nextLong();
    TEST_ENTITY.setId(id);
    assertEquals(id, TEST_ENTITY.getId());
  }

  /** Method under test: {@link BaseEntity#setCreateDate(ZonedDateTime)} */
  @Test
  void testSetCreateDate() {
    var dateTime = ZonedDateTime.now();
    TEST_ENTITY.setCreateDate(dateTime);
    assertEquals(dateTime, TEST_ENTITY.getCreateDate());
  }

  /** Method under test: {@link BaseEntity#setUpdateDate(ZonedDateTime)} */
  @Test
  void testSetUpdateDate() {
    var dateTime = ZonedDateTime.now();
    TEST_ENTITY.setUpdateDate(dateTime);
    assertEquals(dateTime, TEST_ENTITY.getUpdateDate());
  }
}
