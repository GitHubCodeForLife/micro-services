package com.programmingtechie.inventoryservice.utils;

import com.programmingtechie.inventoryservice.model.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.sql.DataSource;
import java.util.Properties;

public class HibernateUtil {
    public static void main(String[] args) {
        //1. create configuration
        Configuration conf = getConfiguration();
        conf.addAnnotatedClass(Inventory.class);
        //2. create session factory
        SessionFactory sessionFactory = conf.buildSessionFactory();

        //3. initialize session
        Session session = sessionFactory.openSession();

        //4. load Inventory to database
        //session.beginTransaction();
        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone_14");
        inventory.setQuantity(100);
        session.save(inventory);
        //session.getTransaction().commit();

        //get datasource

        //5. Close
        session.close();
        sessionFactory.close();
    }

    private static Configuration getConfiguration() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://192.168.163.128:3306/inventory-service");
        properties.put(Environment.USER, "invertory-user");
        properties.put(Environment.PASS, "invertory-pass");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.POOL_SIZE, "20");

        Configuration conf = new Configuration();
        conf.setProperties(properties);
        return conf;
    }

}
