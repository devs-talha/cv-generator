package com.github.tm_425006.webproject.model;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

    public static Connection getConnection(Properties properties) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(String.format(
                        "jdbc:mysql://%s:%s/%s", properties.getProperty("db-host").trim(),
                        properties.getProperty("db-port").trim(), properties.getProperty("db-name").trim()),
                properties.getProperty("db-user").trim(), properties.getProperty("db-password").trim());

    }
}
