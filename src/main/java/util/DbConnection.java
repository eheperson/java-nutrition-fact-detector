package util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // Handle exceptions properly
            e.printStackTrace();
        }
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
