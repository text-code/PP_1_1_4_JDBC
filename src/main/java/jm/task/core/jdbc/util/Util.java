package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BDu#Hd2q#-h%v45h";

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Properties properties = new Properties();
            properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest");
            properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.put("hibernate.connection.username", "root");
            properties.put("hibernate.connection.password", "BDu#Hd2q#-h%v45h");
            properties.put("hibernate.hbm2ddl.auto", "update");
            properties.put("hibernate.show_sql", "true");

            Configuration configuration = new Configuration()
                    .configure()
                    .addAnnotatedClass(User.class)
                    .addProperties(properties);

            ServiceRegistry registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(registryBuilder);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
