package com.example.jpademo2.configuration;

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
@EnableJpaRepositories(basePackages = "com.example.jpademo2.repository",
        entityManagerFactoryRef = "demo2EntityManagerFactory",
        transactionManagerRef= "demo2TransactionManager"
)
public class Demo2DataSourceConfiguration {

    @Resource
    private HibernateProperties hibernateProperties;

    @Bean
    @ConfigurationProperties("app.datasource.demo2")
    public DataSourceProperties demo2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.demo2.configuration")
    public DataSource demo2DataSource() {
        return demo2DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "demo2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean demo2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(demo2DataSource())
                .packages("com.example.jpademo2.entity")
                .persistenceUnit("demoDb2")
                .build();
        // only needed for change to create-drop
        if (hibernateProperties != null && hibernateProperties.getDdlAuto() != null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
            bean.setJpaProperties(prop);
        }

        return bean;
    }

    @Bean
    public PlatformTransactionManager demo2TransactionManager(
            final @Qualifier("demo2EntityManagerFactory") LocalContainerEntityManagerFactoryBean demo2EntityManagerFactory) {
        return new JpaTransactionManager(demo2EntityManagerFactory.getObject());
    }

}
