package com.tech.kj.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    public DataSourceProperties writeDbProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSourceProperties readDbProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource writeDataSource(){
        return  writeDbProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource readDataSource(){
        return readDbProperties().initializeDataSourceBuilder().build();
    }


}
