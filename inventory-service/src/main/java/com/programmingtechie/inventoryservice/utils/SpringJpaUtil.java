package com.programmingtechie.inventoryservice.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@EnableJpaRepositories
public class SpringJpaUtil {
    /*
    -- spring-boot-starter-data-jpa
    ---- spring-boot-starter-jdbc
    -------- com.zaxxer:HikariCP
    -------- spring-jdbc
    ---- hibernate-core
    ---- spring-data-jpa
    -------- spring-orm
    -------- spring-tx
    -------- spring-data-commons

    //org.springframework.boot.autoconfigure.data.jdbc
    //org.springframework.boot.autoconfigure.data.jpa

    //org.springframework.boot.autoconfigure.orm.jpa
    //org.springframework.boot.autoconfigure.jdbc

    //org.springframework.boot.autoconfigure.transaction
    //org.springframework.boot.autoconfigure.sql.init

    */
    public static void main(String[] args) {
        EntityManager entityManager = getEntityManager(getLocalContainerEntityManagerFactoryBean());

        entityManager.close();


    }

    private static LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(getDataSource());
        emf.setPackagesToScan("com.programmingtechie.inventoryservice.model");
        emf.setJpaProperties(getJpaProperties());
        emf.setJpaVendorAdapter(getJpaVendorAdapter());
        emf.setPersistenceUnitName("mysqlDB");

        return emf;
    }

    private static JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        return jpaVendorAdapter;
    }

    private static EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean emf) {
        EntityManagerFactory factory = emf.getNativeEntityManagerFactory();
        return factory.createEntityManager();
    }


    private static Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    private static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.163.128:3306/inventory-service");
        dataSource.setUsername("invertory-user");
        dataSource.setPassword("invertory-pass");

        return dataSource;
    }

    private static PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
