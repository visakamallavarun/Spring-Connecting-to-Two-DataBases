package com.vvarun.two_database.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.vvarun.two_database.orderrepository",
        entityManagerFactoryRef = "postgresEntityManagerFactoryBean",
        transactionManagerRef = "postgrestransactionManager"
)
@EnableTransactionManagement
public class PostgresEntityManagerConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("postgresDatasource") DataSource dataSource) {
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.vvarun.two_database.orderentity")
                .build();
    }

    @Bean
    public PlatformTransactionManager postgrestransactionManager(@Qualifier("postgresEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryBean){
        return new JpaTransactionManager(postgresEntityManagerFactoryBean.getObject());
    }

}
