package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelManager implements DatabaseCreator {

    private final DbManager dbManager;
    private final RoomManager roomManager;
    private final Connection conn;
    private Statement statement;
    private static HotelManager instance = null;

    private HotelManager() {
        dbManager = DbManager.getInstance();
        conn = dbManager.getConnection();
        roomManager = RoomManager.getInstance();
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

            // Check if the HOTEL table exists, if yes, return
            if (dbManager.doesTableExist("HOTEL")) {
                return;
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
    @Override
    public void insertInitialData() {
        try {
            // Check if HOTEL table exists
            if (!dbManager.doesTableExist("HOTEL")) {
                System.out.println("HOTEL table does not exist.");
                return;
            }

            // Check if HOTEL table has any data
            String checkDataSQL = "SELECT COUNT(*) FROM HOTEL";
            ResultSet rs = dbManager.queryDB(checkDataSQL);

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("HOTEL table already has data.");
                rs.close();
                return;
            }
            // SQL query to insert initial data (two hotel entries)
            String insertHotelSQL = "INSERT INTO HOTEL VALUES "
                    + "('HTL-1', 'Auckland Skyline', 'Auckland', 2, 2, 1), " // First hotel details
                    + "('HTL-2', 'Queenstown Grand', 'Queenstown', 1, 1, 1) "; // Second hotel details

            // Execute the SQL query to insert the data
            dbManager.updateDB(insertHotelSQL);

            System.out.println("HOTEL data inserted successfully");

        } catch (Exception e) {
            // Print error message if insertion fails
            System.out.println("Error inserting initial HOTEL data: " + e.getMessage());
        }
    }

    // Method to create a new hotel entry in the HOTEL table
    public void createNewHotel(String name, String location, int numStandardRooms, int numPremiumRooms, int numSuites) {
        // Input validation
        if (name == null || location == null
                || numStandardRooms < 0 || numPremiumRooms < 0 || numSuites < 0) {
            System.out.println("Invalid hotel data provided");
            return;
        }

        String hotelID = idGenerator();
        // Check if the hotel already exists
        if (getHotelData(hotelID) != null) {
            System.out.println("Hotel with ID '" + hotelID + "' already exists.");
            return;
        }

        try {
            // Create the hotel record with initial room counts
            String sql = "INSERT INTO HOTEL (HOTEL_ID, HOTEL_NAME, HOTEL_LOCATION, STANDARD, PREMIUM, SUITE) "
                    + "VALUES ('" + hotelID + "', '" + name + "', '" + location + "', "
                    + numStandardRooms + ", " + numPremiumRooms + ", " + numSuites + ")";

            dbManager.updateDB(sql);
            System.out.println("Hotel '" + name + "' added successfully.");

            // Create the rooms without updating counts
            createRoomsForHotel(hotelID, numStandardRooms, numPremiumRooms, numSuites);
        } catch (Exception e) {
            System.out.println("Error creating new hotel: " + e.getMessage());
        }
    }

    private void createRoomsForHotel(String hotelID, int standardRooms, int premiumRooms, int suites) {
        try {
            // Just create the rooms without updating counts
            for (int i = 0; i < standardRooms; i++) {
                roomManager.createRoom("Standard", hotelID);
            }
            for (int i = 0; i < premiumRooms; i++) {
                roomManager.createRoom("Premium", hotelID);
            }
            for (int i = 0; i < suites; i++) {
                roomManager.createRoom("Suite", hotelID);
            }
        } catch (Exception e) {
            System.out.println("Error creating rooms: " + e.getMessage());
        }
    }

    // Method to update the name and location of a specific hotel in the HOTEL table
    public void updateHotelDetails(String hotelID, String name, String location) {
        // Input validation
        if (hotelID == null || name == null || location == null) {
            System.out.println("Invalid update data provided");
            return;
        }

        // Verify hotel exists
        if (getHotelData(hotelID) == null) {
            System.out.println("Hotel with ID '" + hotelID + "' does not exist.");
            return;
        }

        try {
            // Only update name and location
            String sql = "UPDATE HOTEL SET "
                    + "HOTEL_NAME = '" + name + "', "
                    + "HOTEL_LOCATION = '" + location + "' "
                    + "WHERE HOTEL_ID = '" + hotelID + "'";

            dbManager.updateDB(sql);
            System.out.println("Hotel ID: '" + hotelID + "' updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating hotel: " + e.getMessage());
        }
    }

    // Method to retrieve and display all hotels from the HOTEL table
    public String viewHotels() {

        // SQL query to select all hotel records from the HOTEL table
        String userQuery = "SELECT * FROM HOTEL";
        ResultSet rs = dbManager.queryDB(userQuery);
        StringBuilder hotelDetails = new StringBuilder();
        int counter = 1;

        try {
            // Iterate through the result set and print each hotel's details
            while (rs.next()) {
                String hotelID = rs.getString("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                String hotelLocation = rs.getString("HOTEL_LOCATION");
                int standardRooms = rs.getInt("STANDARD");
                int premiumRooms = rs.getInt("PREMIUM");
                int suites = rs.getInt("SUITE");
                
                hotelDetails.append("==========================\n");
                hotelDetails.append(String.format("                 HOTEL # %d              \n", counter));
                hotelDetails.append("==========================\n");
                hotelDetails.append(String.format("Hotel ID         : %s\n", hotelID));
                hotelDetails.append(String.format("Hotel Name       : %s\n", hotelName));
                hotelDetails.append(String.format("Hotel Location   : %s\n", hotelLocation));
                hotelDetails.append(String.format("Standard Rooms   : %d\n", standardRooms));
                hotelDetails.append(String.format("Premium Rooms    : %d\n", premiumRooms));
                hotelDetails.append(String.format("Suites           : %d\n", suites));
                hotelDetails.append("-----------------------------------------\n");
                
                counter++;
            }
            rs.close(); // Close the result set

        } catch (SQLException ex) {
            // Log any SQL exceptions that occur while retrieving hotel data
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotelDetails.toString();
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
            return null;
        }
        return hotel; // Return the Hotel object (or null if not found)
    }

    public Hotel getHotelByName(String hotelName) {
        Hotel hotel = null;
        String query = "SELECT * FROM HOTEL WHERE HOTEL_NAME = '" + hotelName + "'";
        try {
            ResultSet rs = dbManager.queryDB(query);
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
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return hotel;
    }

    public String idGenerator() {
        String selectSQL = "SELECT MAX(HOTEL_ID) AS MAX_ID FROM HOTEL";
        try {
            ResultSet rs = dbManager.queryDB(selectSQL);

            if (rs.next()) {
                String maxId = rs.getString("MAX_ID");
                if (maxId != null) {
                    int nextId = Integer.parseInt(maxId.split("-")[1]) + 1;
                    return "HTL-" + nextId;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "HTL-1"; // Default to HTL-1 if no entries exist or error occurs
    }

    public List<String> getHotelNames() {
        List<String> hotelNames = new ArrayList<>();
        String query = "SELECT HOTEL_NAME FROM HOTEL";

        try {
            ResultSet rs = dbManager.queryDB(query);
            while (rs.next()) {
                hotelNames.add(rs.getString("HOTEL_NAME"));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotelNames;
    }

    // Method to return DbManager instance
    public DbManager getDbManager() {
        return dbManager;
    }

    public void clearHotelData(String hotelName) {
        try {
            // Delete all records instead of dropping the table
            dbManager.updateDB("DELETE FROM HOTEL WHERE HOTEL_NAME = '" + hotelName + "'");
        } catch (Exception e) {
            System.out.println("Error clearing hotel data: " + e.getMessage());
        }
    }

    public String getHotelIDByName(String hotelName) {
        String hotelID = null;

        try {
            // Create the prepared statement with the hotel name
            String query = "SELECT HOTEL_ID FROM HOTEL WHERE HOTEL_NAME = '" + hotelName + "'";
            ResultSet rs = dbManager.queryDB(query);

            if (rs.next()) {
                hotelID = rs.getString("HOTEL_ID");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotelID;
    }

}
