package com.tech.kj.config;

import com.tech.kj.domain.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(basePackages = {"com.tech.kj.domain","com.tech.kj.dao.read"},
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager")
public class ReadJPAConfig {

    /*@Bean
    @Qualifier("readEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder readEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }*/
    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(@Qualifier("readDataSource") DataSource dataSource,EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(UserEntity.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager readTransactionManager(@Qualifier("readEntityManagerFactory") LocalContainerEntityManagerFactoryBean readEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(readEntityManagerFactory.getObject()));
    }


}
