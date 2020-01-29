package com.example.jpademo2.configuration;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.jpademo2.repository",
        entityManagerFactoryRef = "demo2EntityManagerFactory",
        transactionManagerRef= "transactionManager"
)
public class Demo2DataSourceConfiguration {

    @Resource
    private HibernateProperties hibernateProperties;

    @Bean
    @ConfigurationProperties("app.datasource.demo2")
    public Properties demo2DataBaseProperties() {
        return new Properties();
    }

    @Bean("demo2hibernateproperties")
    @ConfigurationProperties("app.datasource.hibernate.demo2")
    public Properties demo2HibernateProperties() {
        return new Properties();
    }

    @Bean(name = "demo2DataSource")
    public DataSource demo2DataSource() {
        PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
        bitronixDataSourceBean.setMaxPoolSize(5);
        bitronixDataSourceBean.setUniqueName("Demo2DS");
        bitronixDataSourceBean.setClassName("org.apache.derby.jdbc.EmbeddedXADataSource");
        bitronixDataSourceBean.setDriverProperties(demo2DataBaseProperties());
        bitronixDataSourceBean.setAllowLocalTransactions(true);
        bitronixDataSourceBean.setIgnoreRecoveryFailures(true);
        return bitronixDataSourceBean;
    }

    @Bean(name = "demo2EntityManagerFactory")
    @DependsOn({"transactionManager", "demo2DataSource"})
    public LocalContainerEntityManagerFactoryBean demo2EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("demo2DataSource") DataSource demo2DataSource) {
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(demo2DataSource)
                .packages("com.example.jpademo2.entity")
                .persistenceUnit("demoDb2")
                .jta(true)
                .build();
        bean.setJpaProperties(demo2HibernateProperties());

        return bean;
    }

}
