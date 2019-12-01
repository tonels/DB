package com.dbDemo1.dbConfig;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.dbDemo1.dao.oracle",
        entityManagerFactoryRef = "oraclesqlEntityManager",
        transactionManagerRef = "oraclesqlTransactionManager"
)
public class OracleSqlConfiguration {

    @Bean
    @ConfigurationProperties("spring.oraclesql.datasource")
    public DataSourceProperties oracleDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.oraclesql.datasource.configuration")
    public DataSource oraclesqlDataSource() {
        return oracleDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "oraclesqlEntityManager")
    public LocalContainerEntityManagerFactoryBean oraclesqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(oraclesqlDataSource())
                .packages("com.dbDemo1.entity.oracle")
                .persistenceUnit("oraclesqlPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager oraclesqlTransactionManager(@Qualifier("oraclesqlEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
