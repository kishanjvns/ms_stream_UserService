package com.tech.kj.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.tech.kj.domain","com.tech.kj.repository"},
                        entityManagerFactoryRef = "writeEntityManagerFactory",
                        transactionManagerRef = "writeTransactionManager")
public class WriteJPAConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(@Qualifier("writeDataSource") DataSource dataSource,EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.tech.kj.domain")
                .build();
    }

    @Bean
    public PlatformTransactionManager writeTransactionManager(@Qualifier("writeEntityManagerFactory") LocalContainerEntityManagerFactoryBean writeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(writeEntityManagerFactory.getObject()));
    }
}
