package com.localhost.nutritionfact.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

/**
 * @file DbConnection.java
 * @brief Singleton class for managing database connections.
 * 
 * This class provides a singleton implementation for managing database connections. It ensures that
 * only one instance of the database connection is created and shared across the application, promoting
 * efficient resource utilization and avoiding redundant connection creation.
 * 
 * The class uses the Singleton design pattern to ensure that only one instance of the DbConnection
 * class exists throughout the application lifecycle. It encapsulates the database connection logic,
 * including connection creation, retrieval, opening, and closing.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    /**
     * @brief Private constructor to prevent direct instantiation.
     * 
     * This constructor is private to prevent direct instantiation of the DbConnection class from
     * outside the class itself. It is called internally by the getInstance() method to create the
     * singleton instance of DbConnection.
     * 
     * @throws SQLException If a SQL error occurs during database connection creation.
     */
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

    /**
     * @brief Retrieves the singleton instance of DbConnection.
     * 
     * This method returns the singleton instance of the DbConnection class. If the instance does
     * not exist or the existing connection is closed, a new instance is created and returned.
     * 
     * @return The singleton instance of the DbConnection class.
     * @throws SQLException If a SQL error occurs during database connection creation.
     */
    public static DbConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    /**
     * @brief Retrieves the database connection.
     * 
     * This method returns the database connection managed by the DbConnection instance.
     * 
     * @return The database Connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @brief Opens a new database connection.
     * 
     * This method opens a new database connection using the connection properties specified
     * in the db.properties file. It is used to establish a connection to the database when
     * necessary.
     * 
     * @throws SQLException If a SQL error occurs during database connection.
     */
    public void openConnection() throws SQLException {
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
    
    /**
     * @brief Closes the database connection.
     * 
     * This method closes the database connection managed by the DbConnection instance. It is
     * called when the application no longer needs to access the database or when the connection
     * needs to be released.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @brief Loads database connection properties from the db.properties file.
     * 
     * This method loads the database connection properties (URL, username, password) from the
     * db.properties file located in the resources directory. It reads the properties file and
     * returns a Properties object containing the connection properties.
     * 
     * @return A Properties object containing database connection properties.
     */
    private Properties loadProperties() {
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
