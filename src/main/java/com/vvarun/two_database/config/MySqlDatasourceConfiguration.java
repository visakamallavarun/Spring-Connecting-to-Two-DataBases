package com.vvarun.two_database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MySqlDatasourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSourceProperties mySqlDatasourceProperties(){
        return new DataSourceProperties();
    }

//    @Bean
//    public DataSource mysqlDatasource(@Qualifier("mySqlDatasourceProperties") @Autowired DataSourceProperties mySqlDatasourceProperties){
//        DriverManagerDataSource dataSource=new DriverManagerDataSource();
//        dataSource.setUsername(mySqlDatasourceProperties.getUsername());
//        dataSource.setPassword(mySqlDatasourceProperties.getPassword());
//        dataSource.setUrl(mySqlDatasourceProperties.getUrl());
//        dataSource.setDriverClassName(mySqlDatasourceProperties.getDriverClassName());
//        return dataSource;
//    }


    @Primary
    @Bean
    public DataSource mysqlDatasource(@Qualifier("mySqlDatasourceProperties") DataSourceProperties mySqlDatasourceProperties){
        return mySqlDatasourceProperties.initializeDataSourceBuilder().build();
    }

}
