package com.modsen.api;

import com.modsen.api.config.property.HibernateProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(HibernateProperties.class)
public class ModsenCrudApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ModsenCrudApiApplication.class, args);
  }
}
