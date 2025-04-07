package com.programmingtechie.inventoryservice.utils;

import com.programmingtechie.inventoryservice.model.Inventory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

public class JDBCUtil {
    public static void main(String[] args) {
        DataSource getDataSource = getDataSource();
        JdbcTemplate template = new JdbcTemplate(getDataSource);
        List<Inventory> inventoryList = template.query("select * from t_inventory",
                (rs, rowNum) ->
                        new Inventory(rs.getLong("id"),
                                rs.getString("sku_code"),
                                rs.getInt("quantity")));

        inventoryList.forEach(System.out::println);
    }

    private static DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://192.168.163.128:3306/inventory-service");
//        dataSource.setUsername("invertory-user");
//        dataSource.setPassword("invertory-pass");
//
//        return dataSource;
        //Hiraki datasource
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://192.168.163.128:3306/inventory-service");
        dataSource.setUsername("invertory-user");
        dataSource.setPassword("invertory-pass");
        return dataSource;
    }

}
