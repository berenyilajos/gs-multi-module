package com.example.jpademo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.jpademo.repository",
        entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef= "demoTransactionManager"
)
public class DemoDataSourceConfiguration {

    @Resource
    private HibernateProperties hibernateProperties;

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.demo")
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.demo.configuration")
    public DataSource demoDataSource() {
        return demoDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "demoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(demoDataSource())
                .packages("com.example.jpademo.entity")
                .persistenceUnit("demoDb")
                .build();
        // only needed for change to create-drop
        if (hibernateProperties != null && hibernateProperties.getDdlAuto() != null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
            bean.setJpaProperties(prop);
        }

        return bean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager demoTransactionManager(
            final @Qualifier("demoEntityManagerFactory") LocalContainerEntityManagerFactoryBean demoEntityManagerFactory) {
        return new JpaTransactionManager(demoEntityManagerFactory.getObject());
    }

}
