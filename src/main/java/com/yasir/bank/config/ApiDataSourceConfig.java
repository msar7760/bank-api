package com.yasir.bank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(
      basePackages = "com.yasir.bank.repository.api",
      entityManagerFactoryRef = "bankApiEntityManager",
      transactionManagerRef = "bankApiTransactionManager"
  )
  public class ApiDataSourceConfig {

    @Value("${resolved.db.username}")
    private String dbUserName;
    @Value("${resolved.db.password}")
    private String dbPassword;
    @Value("${resolved.db.jdbcUrl}")
    private String jdbcUrl;
    @Value("${resolved.db.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${resolved.db.idleTimeout}")
    private int idleTimeout;
    @Value("${resolved.db.poolName}")
    private String poolName;
    @Value("${resolved.db.maxLifetime}")
    private int maxLifeTime;
    @Value("${resolved.db.connectionTimeout}")
    private int connectionTimeout;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bankApiEntityManager(DataSource apiDataSource, Properties additionalDBProperties) {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(apiDataSource);
      em.setPackagesToScan("com.yasir.bank.entity");

      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalDBProperties);
      return em;
    }

    @Bean
    @Primary
    public DataSource getApiDataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(jdbcUrl);
      config.setUsername(dbUserName);
      config.setPassword(dbPassword);
      config.setMaximumPoolSize(maximumPoolSize);
      config.setIdleTimeout(idleTimeout);
      config.setPoolName(poolName);
      config.setMaxLifetime(maxLifeTime);
      config.setConnectionTimeout(connectionTimeout);
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public PlatformTransactionManager bankApiTransactionManager(EntityManagerFactory payplusApiEntityManager) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      JpaDialect jpaDialect = new HibernateJpaDialect();
      transactionManager.setEntityManagerFactory(payplusApiEntityManager);
      transactionManager.setJpaDialect(jpaDialect);
      return transactionManager;
    }

  }
