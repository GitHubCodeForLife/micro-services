package com.programmingtechie.inventoryservice.utils;

import com.programmingtechie.inventoryservice.model.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class HibernateUtilWithDataSource {
    public static void main(String[] args) {
        //1. create configuration
        Configuration conf = getConfiguration();
        conf.addAnnotatedClass(Inventory.class);
        //2. create session factory
        SessionFactory sessionFactory = conf.buildSessionFactory();

        //3. initialize session
        Session session = sessionFactory.openSession();

        //4. load Inventory to database
        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone_14");
        inventory.setQuantity(100);
        session.save(inventory);

        //5. Close
        session.close();
        sessionFactory.close();
    }

    private static Configuration getConfiguration() {
        Properties properties = new Properties();
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.POOL_SIZE, "20");
        properties.put(Environment.DATASOURCE, getDataSource());

        Configuration conf = new Configuration();
        conf.setProperties(properties);
        return conf;
    }

    private static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.163.128:3306/inventory-service");
        dataSource.setUsername("invertory-user");
        dataSource.setPassword("invertory-pass");

        return dataSource;
    }

}
