package com.vvarun.two_database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresDatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSourceProperties postgresDatasourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource postgresDatasource(@Qualifier("postgresDatasourceProperties") DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
