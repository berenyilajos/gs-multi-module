package com.example.jpademo.configuration;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
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
import java.util.HashMap;
import java.util.Map;
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

    @Bean(name = "demoDataSource")
    @Primary
    public DataSource demoDataSource() {
        PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
        bitronixDataSourceBean.setMaxPoolSize(5);
        bitronixDataSourceBean.setUniqueName("DemoDS");
        bitronixDataSourceBean.setClassName("org.apache.derby.jdbc.EmbeddedXADataSource");
        Properties xaProperties = new Properties();
        xaProperties.put("databaseName", "derbydb");
        xaProperties.put("connectionAttributes", "create=true");
        bitronixDataSourceBean.setDriverProperties(xaProperties);
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
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.factory_class", "jta");
        properties.put("hibernate.transaction.jta.platform", "com.example.jta.BitronixJtaPlatform");
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(demoDataSource)
                .packages("com.example.jpademo.entity")
                .persistenceUnit("demoDb")
                .jta(true)
                .properties(properties)
                .build();
        // only needed for change to create-drop
        if (hibernateProperties != null && hibernateProperties.getDdlAuto() != null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
            bean.setJpaProperties(prop);
        }

        return bean;
    }

}
