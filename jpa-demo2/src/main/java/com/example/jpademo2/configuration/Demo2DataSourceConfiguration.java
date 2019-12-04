package com.example.jpademo22.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.jpademo2.repository",
        entityManagerFactoryRef = "demo2EntityManagerFactory",
        transactionManagerRef= "demo2TransactionManager"
)
public class Demo2DataSourceConfiguration {

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
        // only needed for create-drop
        Properties prop = new Properties();
        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        bean.setJpaProperties(prop);

        return bean;
    }

    @Bean
    public PlatformTransactionManager demo2TransactionManager(
            final @Qualifier("demo2EntityManagerFactory") LocalContainerEntityManagerFactoryBean demo2EntityManagerFactory) {
        return new JpaTransactionManager(demo2EntityManagerFactory.getObject());
    }

}
