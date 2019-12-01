package com.dbDemo2.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.dbDemo2.repository.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager")
public class Db1Configuration {

    @Bean
    @ConfigurationProperties("tonels.datasource.db1")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("tonels.datasource.db1.configuration")
    public DataSource cardDataSource() {
        return cardDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages("com.dbDemo2.model.db1")
                .build();
    }

    @Bean
    public PlatformTransactionManager db1TransactionManager(final @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory.getObject());
    }

}
