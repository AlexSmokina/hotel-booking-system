/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class RoomManager implements DatabaseCreator {

    private static RoomManager instance = null;
    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private RoomManager() {
        dbManager = DbManager.getInstance();
        conn = dbManager.getConnection();
    }

    public static RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }
        return instance;
    }

    // Method to create the ROOM database table
    @Override
    public void createDatabase() {
        try {
            statement = conn.createStatement();
            // Check if ROOM table exists, if yes, drop it
            if (dbManager.doesTableExist("ROOM")) {
                return;
            }

            // SQL statement to create the ROOM table with necessary fields
            String createRoomDB = "CREATE TABLE ROOM ("
                    + "ROOM_ID VARCHAR(10), "
                    + "ROOM_TYPE VARCHAR(20), "
                    + "PRICE DOUBLE, "
                    + "AVAILABILITY_STATUS VARCHAR(20), "
                    + "DATE_FROM DATE, "
                    + "HOTEL_ID VARCHAR(10))";

            dbManager.updateDB(createRoomDB);
            System.out.println("ROOM table created");

            // Check if ROOM_COUNTER table exists
            if (dbManager.doesTableExist("ROOM_COUNTER")) {
                statement.executeUpdate("DROP TABLE ROOM_COUNTER");
            }

            // SQL statement to create the ROOM_COUNTER table
            String createRoomCounterDB = "CREATE TABLE ROOM_COUNTER ("
                    + "HOTEL_ID VARCHAR(10), "
                    + "ROOM_TYPE VARCHAR(20), "
                    + "CURRENT_COUNT INT)";
            dbManager.updateDB(createRoomCounterDB);
            System.out.println("ROOM_COUNTER table created");

        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insertInitialData() {
        try {
            // Check if ROOM table exists
            if (!dbManager.doesTableExist("ROOM")) {
                System.out.println("ROOM table does not exist.");
                return;
            }
            // Check if ROOM table has any data
            String checkDataSQL = "SELECT COUNT(*) FROM ROOM";
            ResultSet rs = dbManager.queryDB(checkDataSQL);

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("ROOM table already has data.");
                rs.close();
                return;
            }
            // Insert rooms for hotel HTL-1
            createRoom("Standard", "HTL-1");
            createRoom("Standard", "HTL-1");
            createRoom("Premium", "HTL-1");
            createRoom("Premium", "HTL-1");
            createRoom("Suite", "HTL-1");

            // Insert rooms for hotel HTL-2
            createRoom("Standard", "HTL-2");
            createRoom("Premium", "HTL-2");
            createRoom("Suite", "HTL-2");
        } catch (SQLException e) {
            System.out.println("Error inserting initial ROOM data: " + e.getMessage());

        }
    }

    // Method to insert a new room record into the ROOM table
    public void createRoom(String roomType, String hotelID) {
        RoomType type = RoomType.valueOf(roomType.toUpperCase());
        // Generate the room ID based on hotel and room type
        String roomID = idGenerator(new Object[]{hotelID, type});
        // Get today's date as the availability date
        String availabilityDateStr = dateFormat.format(new Date());
        // SQL query to insert the new room data
        String insertRoomSQL = "INSERT INTO ROOM (ROOM_ID, ROOM_TYPE, PRICE, AVAILABILITY_STATUS, DATE_FROM, HOTEL_ID) VALUES ("
                + "'" + roomID + "', " // Room ID
                + "'" + roomType.toUpperCase() + "', "
                + type.getPrice() + ", "
                + "'Available', "
                + "'" + availabilityDateStr + "', "
                + "'" + hotelID + "')";

        dbManager.updateDB(insertRoomSQL);

        String roomCountColumn = getRoomColumnName(roomType);

    }

    // Method to delete ROOM
    public boolean removeRoom(String roomID, String hotelID) {
        try {
            // Get the room type before deleting, to update the hotel room count correctly
            String getRoomTypeSQL = "SELECT ROOM_TYPE FROM ROOM WHERE ROOM_ID = '" + roomID + "'";
            ResultSet rs = dbManager.queryDB(getRoomTypeSQL);

            if (!rs.next()) {
                System.out.println("Room not found.");
                return false;
            }

            // Retrieve the room type
            String roomType = rs.getString("ROOM_TYPE").toUpperCase();
            RoomType type = RoomType.valueOf(roomType);

            // Delete the room from the ROOM table
            String deleteRoomSQL = "DELETE FROM ROOM WHERE ROOM_ID = '" + roomID + "'";
            dbManager.updateDB(deleteRoomSQL);
            System.out.printf("Room %s removed successfully.%n", roomID);

            // Determine which room count column to decrement in the HOTEL table
            String roomCountColumn = getRoomColumnName(roomType);

            // Update the hotel room count in the HOTEL table
            String updateHotelSQL = "UPDATE HOTEL SET " + roomCountColumn + " = " + roomCountColumn + " - 1 WHERE HOTEL_ID = '" + hotelID + "'";
            dbManager.updateDB(updateHotelSQL);

            // Update the ROOM_COUNTER table
            String updateCounterSQL = "UPDATE ROOM_COUNTER SET CURRENT_COUNT = CURRENT_COUNT - 1 "
                    + "WHERE HOTEL_ID = '" + hotelID + "' AND ROOM_TYPE = '" + roomType + "'";
            dbManager.updateDB(updateCounterSQL);
            return true;

        } catch (SQLException ex) {
            System.out.println("Error removing room: " + ex.getMessage());
            return false;
        }
    }

    // Method to generate a unique room ID by incrementing a counter
    public String idGenerator(Object context) {
        if (!(context instanceof Object[])) {
            throw new IllegalArgumentException("Context must be an array with RoomType and HotelID.");
        }

        Object[] contextArray = (Object[]) context;
        String hotelID = (String) contextArray[0];
        RoomType roomType = (RoomType) contextArray[1];

        // Get the next room count for the given hotel and room type
        int currentID = getNextRoomCount(hotelID, roomType.name());

        // Prefix the room ID based on the room type
        String prefix = "RM/";
        switch (roomType) {
            case SUITE:
                prefix += "SU-";
                break;
            case PREMIUM:
                prefix += "PRM-";
                break;
            default:
                prefix += "STD-";
                break;
        }
        return prefix + currentID; // Return the full room ID
    }

    // Method to get the next available room count for a specific hotel and room type
    private int getNextRoomCount(String hotelID, String roomType) {
        try {
            // SQL query to get the current room count for the hotel and room type
            String selectSQL = "SELECT CURRENT_COUNT FROM ROOM_COUNTER WHERE HOTEL_ID = '"
                    + hotelID + "' AND ROOM_TYPE = '" + roomType + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);

            if (rs.next()) {
                // Increment the count if it exists and update the database
                int newCount = rs.getInt("CURRENT_COUNT") + 1;
                updateRoomCount(hotelID, roomType, newCount);
                return newCount;
            } else {
                // Insert a new entry if no previous count exists
                insertRoomCount(hotelID, roomType, 1);
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
            return 0; // Return 0 in case of an error
        }
    }

    // Method to update the room count in the ROOM_COUNTER table
    private void updateRoomCount(String hotelID, String roomType, int newCount) {
        try {
            // SQL query to update the room count
            String updateSQL = "UPDATE ROOM_COUNTER SET CURRENT_COUNT = " + newCount
                    + " WHERE HOTEL_ID = '" + hotelID
                    + "' AND ROOM_TYPE = '" + roomType + "'";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(updateSQL);
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to insert a new room count entry in the ROOM_COUNTER table
    private void insertRoomCount(String hotelID, String roomType, int count) {
        try {
            // SQL query to insert a new room count
            String insertSQL = "INSERT INTO ROOM_COUNTER (HOTEL_ID, ROOM_TYPE, CURRENT_COUNT) VALUES ('"
                    + hotelID + "', '"
                    + roomType + "', "
                    + count + ")";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to get room data from the database based on room ID and hotel ID
    public Room getRoomData(String roomID, String hotelID) {
        try {
            Statement stmt = conn.createStatement();
            // SQL query to retrieve room data
            String query = "SELECT * FROM ROOM WHERE ROOM_ID = '"
                    + roomID + "' AND HOTEL_ID = '" + hotelID + "'";

            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return extractRoomFromResultSet(rs); // Extract room data
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Method to update room data in the ROOM table
    public boolean updateRoomData(String roomID, Room newRoomData) {
        try {
            Statement stmt = conn.createStatement();
            // SQL query to update room details
            String updateSQL = "UPDATE ROOM SET "
                    + "ROOM_TYPE = '" + newRoomData.getRoomType() + "', "
                    + "PRICE = " + newRoomData.getPrice() + ", "
                    + "AVAILABILITY_STATUS = '" + (newRoomData.isBooked() ? "Booked" : "Available") + "', "
                    + "DATE_FROM = '" + dateFormat.format(newRoomData.getAvailabilityDate()) + "' "
                    + "WHERE ROOM_ID = '" + roomID + "' "
                    + "AND HOTEL_ID = '" + newRoomData.getHotelID() + "'";

            return stmt.executeUpdate(updateSQL) > 0; // Return true if update is successful
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Return false if update fails
        }
    }

    // Method to filter rooms by hotel ID and return a list of rooms
    public List<Room> filterRoomByHotel(String hotelID) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            // SQL query to filter rooms by hotel ID
            String query = "SELECT * FROM ROOM WHERE HOTEL_ID = '" + hotelID + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roomList.add(extractRoomFromResultSet(rs)); // Add each room to the list
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomList; // Return the list of rooms
    }

    // Method to filter rooms by availability date and hotel ID
    public List<Room> filterByDate(String date, String hotelName) {
        List<Room> roomList = new ArrayList<>();

        // First, get the hotel ID from hotel name
        try {
            Statement stmt = conn.createStatement();

            String hotelQuery = "SELECT HOTEL_ID FROM HOTEL WHERE HOTEL_NAME = '" + hotelName + "'";
            ResultSet hotelRs = stmt.executeQuery(hotelQuery);

            // Check if hotel exists and get its ID
            String hotelID = null;
            if (hotelRs.next()) {
                hotelID = hotelRs.getString("HOTEL_ID");
            } else {
                // If hotel not found, return empty list
                return roomList;
            }
            hotelRs.close();
            // SQL query to filter rooms by availability date and hotel ID
            String query = "SELECT * FROM ROOM WHERE DATE_FROM <= '" + date
                + "' AND HOTEL_ID = '" + hotelID + "' AND AVAILABILITY_STATUS != 'Booked'";

            ResultSet rs = dbManager.queryDB(query);
            while (rs.next()) {
                roomList.add(extractRoomFromResultSet(rs)); // Add each room to the list
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomList; // Return the list of rooms
    }

    public String viewRooms() {
        // SQL query to select all room records from the ROOM table
        String userQuery = "SELECT * FROM ROOM";
        ResultSet rs = dbManager.queryDB(userQuery);
        StringBuilder roomDetails = new StringBuilder();

        // Header for the room details
        roomDetails.append(String.format("%s, %s, %s, %s, %s, %s\n",
                "Room ID", "Room Type", "Price", "Availability Status", "Date From", "Hotel ID"));
        roomDetails.append("===================\n");

        try {
            // Iterate through the result set and print each room's details
            while (rs.next()) {
                String roomID = rs.getString("ROOM_ID");
                String roomType = rs.getString("ROOM_TYPE");
                double price = rs.getDouble("PRICE");
                String availabilityStatus = rs.getString("AVAILABILITY_STATUS");
                Date dateFrom = rs.getDate("DATE_FROM");
                String hotelID = rs.getString("HOTEL_ID");

                // Append room details to the StringBuilder
                roomDetails.append(String.format("%s, %s, %.2f, %s, %s, %s\n",
                        roomID, roomType, price, availabilityStatus,
                        (dateFrom != null ? dateFrom.toString() : "N/A"), hotelID));
            }
            rs.close(); // Close the result set

        } catch (SQLException ex) {
            // Log any SQL exceptions that occur while retrieving room data
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomDetails.toString();
    }

    // Helper method to extract room data from a ResultSet
    private Room extractRoomFromResultSet(ResultSet rs) throws SQLException {
        String roomID = rs.getString("ROOM_ID");
        RoomType roomType = RoomType.valueOf(rs.getString("ROOM_TYPE"));
        String hotelID = rs.getString("HOTEL_ID");

        Room room = new Room(roomID, roomType, hotelID); // Create a new Room object
        room.setIsBooked(rs.getString("AVAILABILITY_STATUS").equals("Booked")); // Set booking status
        room.setAvailabilityDate(rs.getDate("DATE_FROM").toString()); // Set availability date
        return room; // Return the Room object
    }

    public void clearRoomData() {
        try {
            // Check if tables exist before trying to clear them
            if (dbManager.doesTableExist("ROOM")) {
                dbManager.updateDB("DELETE FROM ROOM");
            }

            if (dbManager.doesTableExist("ROOM_COUNTER")) {
                dbManager.updateDB("DELETE FROM ROOM_COUNTER");
            }
        } catch (Exception e) {
            System.err.println("Error clearing room data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to get the column name
    private String getRoomColumnName(String roomType) {
        switch (roomType.toUpperCase()) {
            case "STANDARD":
                return "STANDARD";
            case "PREMIUM":
                return "PREMIUM";
            case "SUITE":
                return "SUITE";
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

}
