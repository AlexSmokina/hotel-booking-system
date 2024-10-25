package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelManager implements DatabaseCreator {

    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;
    private static HotelManager instance = null;

    private HotelManager() {
        dbManager = new DbManager();
        conn = dbManager.getConnection();
    }

    public static HotelManager getInstance() {
        if (instance == null) {
            instance = new HotelManager();
        }
        return instance;
    }

// Method to create the HOTEL table in the database
    @Override
    public void createDatabase() {
        try {
            // Create a new SQL statement
            statement = conn.createStatement();

            // Check if the HOTEL table exists, if yes, drop it
            if (dbManager.doesTableExist("HOTEL")) {
                statement.executeUpdate("DROP TABLE HOTEL");
            }

            // SQL query to create the HOTEL table with necessary columns
            String createHotelTableSQL = "CREATE TABLE HOTEL ("
                    + "HOTEL_ID VARCHAR(10), "
                    + "HOTEL_NAME VARCHAR(50), "
                    + "HOTEL_LOCATION VARCHAR(20), "
                    + "STANDARD INT, " // Number of Standard rooms
                    + "PREMIUM INT, " // Number of Premium rooms
                    + "SUITE INT)";     // Number of Suite rooms

            // Execute the SQL query to create the table
            dbManager.updateDB(createHotelTableSQL);

        } catch (SQLException ex) {
            // Log any SQL exceptions that occur during table creation
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Method to insert initial hotel data into the HOTEL table
    public void insertInitialData() {
        try {
            // SQL query to insert initial data (two hotel entries)
            String insertHotelSQL = "INSERT INTO HOTEL VALUES "
                    + "('HTL-1', 'Auckland Skyline', 'Auckland', 5, 3, 2), " // First hotel details
                    + "('HTL-2', 'Queenstown Grand', 'Queenstown', 4, 4, 1) "; // Second hotel details

            // Execute the SQL query to insert the data
            dbManager.updateDB(insertHotelSQL);

            System.out.println("HOTEL data inserted successfully");

        } catch (Exception e) {
            // Print error message if insertion fails
            System.out.println("Error inserting initial HOTEL data: " + e.getMessage());
        }
    }

// Method to create a new hotel entry in the HOTEL table
    public void createNewHotel(String hotelID, String name, String location, int numStandardRooms, int numPremiumRooms, int numSuites) {
        // Check if the hotel already exists by looking up its hotel ID
        if (getHotelData(hotelID) != null) {
            System.out.println("Hotel with ID '" + hotelID + "' already exists.");
            return; // Exit the method if hotel already exists
        }

        // SQL query to insert the new hotel data into the HOTEL table
        String sql = "INSERT INTO HOTEL (HOTEL_ID, HOTEL_NAME, HOTEL_LOCATION, STANDARD, PREMIUM, SUITE) "
                + "VALUES ('" + hotelID + "', '" + name + "', '" + location + "', "
                + numStandardRooms + ", " + numPremiumRooms + ", " + numSuites + ")";

        try {
            // Execute the SQL query to add the new hotel
            dbManager.updateDB(sql);
            System.out.println("Hotel '" + name + "' added successfully.");
        } catch (Exception e) {
            // Print error message if hotel creation fails
            System.out.println("Error creating new hotel: " + e.getMessage());
        }
    }

// Method to update the name and location of a specific hotel in the HOTEL table
    public void updateHotelDetails(String hotelID, String name, String location) {

        // SQL query to update the hotel's name and location based on its hotel ID
        String sql = "UPDATE HOTEL SET "
                + "HOTEL_NAME = '" + name + "', "
                + "HOTEL_LOCATION = '" + location + "'"
                + "WHERE HOTEL_ID = '" + hotelID + "'";

        try {
            // Execute the SQL query to update the hotel details
            dbManager.updateDB(sql);
            System.out.println("Hotel ID: '" + hotelID + "' updated successfully");
        } catch (Exception e) {
            // Print error message if hotel update fails
            System.out.println("Error updating hotel: " + e.getMessage());
        }
    }

// Method to retrieve and display all hotels from the HOTEL table
    public void viewHotels() {
        // SQL query to select all hotel records from the HOTEL table
        String userQuery = "SELECT * FROM HOTEL";
        ResultSet rs = dbManager.queryDB(userQuery);

        try {
            // Iterate through the result set and print each hotel's details
            while (rs.next()) {
                String hotelID = rs.getString("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                String hotelLocation = rs.getString("HOTEL_LOCATION");
                int standardRooms = rs.getInt("STANDARD");
                int premiumRooms = rs.getInt("PREMIUM");
                int suites = rs.getInt("SUITE");

                // Print the hotel details to the console
                System.out.println("Hotel ID: " + hotelID + ", Name: " + hotelName
                        + ", Location: " + hotelLocation
                        + ", Standard Rooms: " + standardRooms
                        + ", Premium Rooms: " + premiumRooms
                        + ", Suites: " + suites);
            }
            rs.close(); // Close the result set

        } catch (SQLException ex) {
            // Log any SQL exceptions that occur while retrieving hotel data
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Method to retrieve hotel data based on the hotel ID
    public Hotel getHotelData(String hotelID) {
        Hotel hotel = null;
        // SQL query to select a hotel by its hotel ID
        String hotelQuery = "SELECT * FROM HOTEL WHERE HOTEL_ID = '" + hotelID + "'";

        try {
            ResultSet rs = dbManager.queryDB(hotelQuery);

            // If a matching hotel is found, extract its data and create a Hotel object
            if (rs.next()) {
                String id = rs.getString("HOTEL_ID");
                String name = rs.getString("HOTEL_NAME");
                String location = rs.getString("HOTEL_LOCATION");
                int standardRooms = rs.getInt("STANDARD");
                int premiumRooms = rs.getInt("PREMIUM");
                int suites = rs.getInt("SUITE");

                // Create a Hotel object and set its room counts
                hotel = new Hotel(id, name, location);
                hotel.setNumStandardRooms(standardRooms);
                hotel.setNumPremiumRooms(premiumRooms);
                hotel.setNumSuites(suites);
                rs.close(); // Close the result set
            }

        } catch (SQLException ex) {
            // Log any SQL exceptions that occur while retrieving hotel data
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotel; // Return the Hotel object (or null if not found)
    }

}
