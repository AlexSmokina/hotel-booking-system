package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database connection manager for HotelBookingSystemDB
 */
public final class HBS_DBManager {
    private static final String USER = "hbs"; // Default user for Derby
    private static final String PASSWORD = "hbs"; // Default password for Derby
    private static final String URL = "jdbc:derby://localhost:1527/HotelBookingSystemDB;create=true";
    
    private Connection conn;

    public HBS_DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(URL + "   CONNECTED....");
            } catch (SQLException ex) {
                System.out.println("Error establishing connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException ex) {
                Logger.getLogger(HBS_DBManager.class.getName()).log(Level.SEVERE, "Error closing connection", ex);
            }
        }
    }

    public ResultSet myQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    public void myUpdate(String sql) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
            e.printStackTrace();
        }
    }
}