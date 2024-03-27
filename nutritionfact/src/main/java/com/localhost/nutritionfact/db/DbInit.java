package com.localhost.nutritionfact.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * @file DbInit.java
 * @brief Class for initializing the database schema using an SQL script file.
 * 
 * This class provides functionality for initializing the database schema using an SQL script file.
 * It contains methods to load the SQL script from a file, execute the SQL commands, and close
 * database resources after execution. The SQL script file is expected to be located in the
 * `src/main/resources/` directory with the name `db/schema.sql`.
 * 
 * @author [Your Name]
 * @date [Date]
 */
public class DbInit {

     /**
     * @brief Initializes the database schema using the SQL script file.
     * 
     * This method initializes the database schema by loading the SQL script file containing
     * the schema definition and executing the SQL commands. The path to the SQL script file
     * is relative to the `src/main/resources/` directory and is defined as `db/schema.sql`.
     * 
     * @post If the SQL script file is successfully loaded and contains non-empty SQL commands,
     *       the database schema is initialized with the tables and schema specified in the script.
     *       If an error occurs during SQL script loading or execution, the database may not be
     *       properly initialized, and an error message may be printed to the console.
     */
    public static void initializeDatabase() {
        // The path is relative to the `src/main/resources/` directory
        String sqlScriptPath = "db/schema.sql";
        String sql = loadSqlFromFile(sqlScriptPath);
        
        if (sql != null && !sql.trim().isEmpty()) {
            executeSql(sql);
        }
    }

    /**
     * @brief Loads SQL commands from an SQL script file.
     * 
     * This method reads SQL commands from the specified SQL script file and returns them as
     * a single string. It loads the SQL script file from the resources directory, assuming
     * the file path is relative to the `src/main/resources/` directory.
     * 
     * @param path The path to the SQL script file relative to the resources directory.
     * @return A string containing the SQL commands read from the script file, or null if
     *         an error occurs during file loading.
     */
    private static String loadSqlFromFile(String path) {
        try (InputStream is = DbInit.class.getClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            System.out.println("Failed to load SQL file: " + path);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @brief Executes SQL commands.
     * 
     * This method executes SQL commands provided as a string. It splits the string into
     * individual SQL statements and executes them one by one using a database connection.
     * 
     * @param sql The SQL commands to execute.
     */
    private static void executeSql(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DbConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            String[] statements = sql.split(";");
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement.trim());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * @brief Closes a database resource.
     * 
     * This method closes a database resource such as a connection or statement. It is used
     * to ensure proper cleanup of database resources after execution of SQL commands.
     * 
     * @param resource The database resource to close.
     */
    private static void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.out.println("Failed to close resource: " + e.getMessage());
            }
        }
    }
}
