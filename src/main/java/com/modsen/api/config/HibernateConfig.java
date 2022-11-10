package com.modsen.api.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.modsen.api.config.property.HibernateProperties;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HibernateConfig {
  DataSourceProperties dataSourceProperties;
  HibernateProperties hibernateProperties;

  @Bean
  @SneakyThrows(PropertyVetoException.class)
  public DataSource dataSource() {
    var dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass(dataSourceProperties.getDriverClassName());
    dataSource.setJdbcUrl(dataSourceProperties.getUrl());
    dataSource.setUser(dataSourceProperties.getUsername());
    dataSource.setPassword(dataSourceProperties.getPassword());
    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    var sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(hibernateProperties.getPackagesToScan());
    sessionFactory.setHibernateProperties(hibernateProperties.getDefault());
    return sessionFactory;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    var transactionManager = new HibernateTransactionManager();
    var sessionFactory = sessionFactory().getObject();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }
}
