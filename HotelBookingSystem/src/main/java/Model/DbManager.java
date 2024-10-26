/*
 * The programs are designed for PDC paper
 */
package Model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbManager {

// Database connection credentials
    private static final String USER_NAME = "hbs"; // Default user for Derby
    private static final String PASSWORD = "hbs"; // Default password for Derby
    private static final String URL = "jdbc:derby:derbyDatabase/HotelBookingSystemDB;create=true";
    ;  // Extra semicolon should be removed

    // Active database connection
    Connection conn;
    
    // Singleton instance holder
    private static DbManager instance = null;
    
    // Private constructor to prevent direct instantiation
    private DbManager() {
        establishConnection();
    }

    // Thread-safe singleton instance getter
    public static synchronized DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    // Returns the current database connection
    public Connection getConnection() {
        return this.conn;
    }

    // Establishes a new database connection using Derby driver
    // Prints success message on connection, error message on failure
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println(URL + " connected");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    // Safely closes the database connection if it exists
    // Should be called when shutting down the application
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Executes a SELECT query and returns the result set
    // @param sql The SQL SELECT query to execute
    // @return ResultSet containing query results, or null if query fails
    public ResultSet queryDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    // Executes an UPDATE, INSERT, or DELETE query
    // @param sql The SQL modification query to execute
    public void updateDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Checks if a table exists in the database
    // @param tableName Name of the table to check
    // @return true if table exists, false otherwise
    public boolean doesTableExist(String tableName) {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, null, tableName.toUpperCase(), null);
            boolean tableExists = rs.next();
            rs.close();
            return tableExists;
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return false;
    }

}
