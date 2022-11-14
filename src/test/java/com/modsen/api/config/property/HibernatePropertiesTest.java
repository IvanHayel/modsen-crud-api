package com.modsen.api.config.property;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HibernatePropertiesTest {
  private static final String TEST_DIALECT = "Dialect";
  private static final String TEST_ROOT_DIRECTORY = "/directory/root";
  private static final String TEST_LUCENE_VERSION = "1.0.3";
  private static final String[] TEST_PACKAGES_TO_SCAN = {"java.text", "java.lang"};
  private static final int DEFAULT_PROPERTIES_COUNT = 3;

  /** Method under test: {@link HibernateProperties#getDefault()} */
  @Test
  void testGetDefault() {
    var hibernateProperties = new HibernateProperties();
    hibernateProperties.setDialect(TEST_DIALECT);
    hibernateProperties.setLuceneRootDirectory(TEST_ROOT_DIRECTORY);
    hibernateProperties.setLuceneVersion(TEST_LUCENE_VERSION);
    hibernateProperties.setPackagesToScan(TEST_PACKAGES_TO_SCAN);
    assertEquals(DEFAULT_PROPERTIES_COUNT, hibernateProperties.getDefault().size());
    assertArrayEquals(TEST_PACKAGES_TO_SCAN, hibernateProperties.getPackagesToScan());
  }
}
