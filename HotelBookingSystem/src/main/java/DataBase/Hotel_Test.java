package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hotel class to test database connection using HBS_DBManager
 */
public class Hotel_Test {
    private static HBS_DBManager dbManager;

    public static void main(String[] args) {
        dbManager = new HBS_DBManager();
        Connection connection = dbManager.getConnection();
        
        if (connection != null) {
            System.out.println("Connection to HotelBookingSystemDB successful!");
            createTable();
            insertTestData();
            retrieveData();
        } else {
            System.out.println("Failed to connect to the database.");
        }
        
        dbManager.closeConnections();
    }

    private static void createTable() {
        String createTableSQL = "CREATE TABLE Hotel ("
                + "ID INT PRIMARY KEY, "
                + "NAME VARCHAR(100), "
                + "LOCATION VARCHAR(100))";
        try {
            dbManager.myUpdate(createTableSQL);
            System.out.println("Table 'Hotel' created successfully.");
        } catch (Exception e) {
            if (e.getMessage().contains("X0Y32")) {
                System.out.println("Table 'Hotel' already exists.");
            } else {
                System.out.println("Error creating table: " + e.getMessage());
            }
        }
    }

    private static void insertTestData() {
        String insertSQL = "INSERT INTO Hotel (ID, NAME, LOCATION) "
                + "VALUES (1, 'Grand Palace', 'Auckland'), "
                + "(2, 'Sea View Resort', 'Wellington')";
        try {
            dbManager.myUpdate(insertSQL);
            System.out.println("Test data inserted successfully.");
        } catch (Exception e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    private static void retrieveData() {
        String querySQL = "SELECT * FROM Hotel";
        try {
            ResultSet rs = dbManager.myQuery(querySQL);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String location = rs.getString("LOCATION");
                System.out.println("ID: " + id + ", Name: " + name + ", Location: " + location);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving data: " + e.getMessage());
        }
    }
}