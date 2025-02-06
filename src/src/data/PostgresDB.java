package data;

import java.sql.*;

public class PostgresDB implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;
    private static PostgresDB instance;


    private Connection connection;

    private PostgresDB() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Endterm_Project";
            String user = "postgres";
            String password = "0000";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public static PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Connection close error: " + ex.getMessage());
            }
        }
    }
}