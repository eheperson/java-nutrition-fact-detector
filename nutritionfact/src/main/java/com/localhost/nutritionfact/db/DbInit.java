package com.localhost.nutritionfact.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DbInit {

    public static void initializeDatabase() {
        // The path is relative to the `src/main/resources/` directory
        String sqlScriptPath = "db/schema.sql";
        String sql = loadSqlFromFile(sqlScriptPath);
        
        if (sql != null && !sql.trim().isEmpty()) {
            executeSql(sql);
        }
    }

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
