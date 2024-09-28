package com.github.tm_425006.webproject;

import javax.servlet.ServletContext;
import java.sql.Date;
import java.util.Properties;

public class Utils {
    public static Properties servletContextToProperties(ServletContext servletContext) {
        Properties properties = new Properties();
        properties.setProperty("db-host", servletContext.getInitParameter("db-host"));
        properties.setProperty("db-port", servletContext.getInitParameter("db-port"));
        properties.setProperty("db-name", servletContext.getInitParameter("db-name"));
        properties.setProperty("db-user", servletContext.getInitParameter("db-user"));
        properties.setProperty("db-password", servletContext.getInitParameter("db-password"));
        return properties;
    }
}
