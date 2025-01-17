package com.rfid.mhifes.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.rfid.mhifes.repository.postgres", entityManagerFactoryRef = "postgresEntityManagerFactory", transactionManagerRef = "postgresTransactionManager")
public class PostgresConfig {

	@Primary
	@Bean(name = "postgresDataSource")
	@ConfigurationProperties(prefix = "postgres.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "postgresEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("postgresDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.rfid.mhifes.model.postgres").persistenceUnit("postgres")
				.build();
	}

	@Primary
	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	// @Primary
    // @Bean(name = "postgresEntityManagerFactory")
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
    //         @Qualifier("postgresDataSource") DataSource dataSource) {
    //     Map<String, Object> properties = new HashMap<>();
    //     properties.put("hibernate.hbm2ddl.auto", "create");
    //     properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

    //     return builder.dataSource(dataSource).packages("com.rfid.mhifes.model.postgres").persistenceUnit("postgres")
    //             .properties(properties).build();
    // }
}
