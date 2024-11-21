package com.vvarun.two_database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilderCustomizer;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(
        basePackages = "com.vvarun.two_database.productrepository",
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean",
        transactionManagerRef = "mySqltransactionManager"
)
public class MySqlEntityManagerConfig {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("mysqlDatasource") DataSource dataSource) {

        Properties jpaProperties = new Properties();
        jpaProperties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.vvarun.two_database.productentity")
                .build();
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager mySqltransactionManager(@Qualifier("mySqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean){
        return new JpaTransactionManager(mySqlEntityManagerFactoryBean.getObject());
    }

}
