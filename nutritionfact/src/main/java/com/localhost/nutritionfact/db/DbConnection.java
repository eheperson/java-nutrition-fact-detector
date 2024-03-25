package com.localhost.nutritionfact.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() throws SQLException {
        try {
            Properties prop = loadProperties();
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private Properties loadProperties() {
        // String classpath = System.getProperty("java.class.path");
        // System.out.println("Current Classpath: " + classpath);
        try (InputStream input = DbConnection.class.getClassLoader().getResourceAsStream("db/db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            prop.load(input);
            return prop;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
