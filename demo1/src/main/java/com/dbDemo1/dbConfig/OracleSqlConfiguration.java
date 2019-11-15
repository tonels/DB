package com.dbDemo1.dbConfig;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "oraclesqlEntityManager",
        transactionManagerRef = "oraclesqlTransactionManager",
        basePackages = "com.dbDemo1.dao.oracle"
)
public class OracleSqlConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.oraclesql.datasource")
    public DataSource oraclesqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "oraclesqlEntityManager")
    public LocalContainerEntityManagerFactoryBean oraclesqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(oraclesqlDataSource())
                .packages("com.db.entity")
                .persistenceUnit("oraclesqlPU").build();
    }

    @Primary
    @Bean(name = "oraclesqlTransactionManager")
    public PlatformTransactionManager oraclesqlTransactionManager(@Qualifier("oraclesqlEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
