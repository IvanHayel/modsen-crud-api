package com.modsen.api.config.property;

import java.util.Properties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hibernate")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HibernateProperties {
  public static final String DIALECT_PROPERTY_NAME = "hibernate.dialect";
  public static final String LUCENE_VERSION = "hibernate.search.backend.lucene_version";
  public static final String LUCENE_ROOT_DIRECTORY = "hibernate.search.backend.directory.root";

  String dialect;
  String[] packagesToScan;
  String luceneVersion;
  String luceneRootDirectory;

  public Properties getDefault() {
    var properties = new Properties();
    properties.setProperty(DIALECT_PROPERTY_NAME, dialect);
    properties.setProperty(LUCENE_VERSION, luceneVersion);
    properties.setProperty(LUCENE_ROOT_DIRECTORY, luceneRootDirectory);
    return properties;
  }
}
