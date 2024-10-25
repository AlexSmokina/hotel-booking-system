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
                statement.executeUpdate("DROP TABLE ROOM");
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
        System.out.printf("Room %s created successfully.%n", roomID);
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
    public List<Room> filterByDate(String date, String hotelID) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            // SQL query to filter rooms by availability date and hotel ID
            String query = "SELECT * FROM ROOM WHERE DATE_FROM = '" + date
                    + "' AND HOTEL_ID = '" + hotelID + "'";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                roomList.add(extractRoomFromResultSet(rs)); // Add each room to the list
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomList; // Return the list of rooms
    }

    // Method to get a list of all room IDs in the ROOM table
    public List<String> getAllRoomIDs() {
        List<String> roomIDs = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            // SQL query to retrieve all room IDs
            String query = "SELECT ROOM_ID FROM ROOM";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roomIDs.add(rs.getString("ROOM_ID")); // Add each room ID to the list
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roomIDs; // Return the list of room IDs
    }

    // Method to get a list of all rooms in the ROOM table
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            // SQL query to retrieve all rooms
            String query = "SELECT * FROM ROOM";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                rooms.add(extractRoomFromResultSet(rs)); // Add each room to the list
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms; // Return the list of rooms
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

}
