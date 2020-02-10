package com.example.jpademo.configuration;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.jpademo.repository",
        entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef= "transactionManager"
)
public class DemoDataSourceConfiguration {

    @Resource
    private HibernateProperties hibernateProperties;

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.demo")
    public Properties demoDataBaseProperties() {
        return new Properties();
    }

    @Bean("demohibernateproperties")
    @ConfigurationProperties("app.datasource.hibernate.demo")
    public Properties demoHibernateProperties() {
        return new Properties();
    }

    @Bean(name = "demoDataSource")
    @Primary
    public DataSource demoDataSource() {
        PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
        bitronixDataSourceBean.setMaxPoolSize(5);
        bitronixDataSourceBean.setUniqueName("DemoDS");
        bitronixDataSourceBean.setClassName("org.apache.derby.jdbc.EmbeddedXADataSource");
        bitronixDataSourceBean.setDriverProperties(demoDataBaseProperties());
        bitronixDataSourceBean.setAllowLocalTransactions(true);
        bitronixDataSourceBean.setIgnoreRecoveryFailures(true);
        return bitronixDataSourceBean;
    }

    @Primary
    @Bean(name = "demoEntityManagerFactory")
    @DependsOn({"transactionManager", "demoDataSource"})
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource demoDataSource) {
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(demoDataSource)
                .packages("com.example.jpademo.entity")
                .persistenceUnit("demoDb")
                .jta(true)
                .build();
        bean.setJpaProperties(demoHibernateProperties());

        return bean;
    }

}
