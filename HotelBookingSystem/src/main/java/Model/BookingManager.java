/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class BookingManager implements DatabaseCreator {

    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;
    RoomManager roomManager;
    UserManager userManager;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static BookingManager instance = null;

    // Constructor to initialize the database connection
    private BookingManager() {
        dbManager = DbManager.getInstance();
        conn = dbManager.getConnection();
        roomManager = RoomManager.getInstance();
        userManager = UserManager.getInstance();
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    // Method to create the Booking table in the database
    @Override
    public void createDatabase() {
        try {

            statement = conn.createStatement();
            // Return if BOOKING exists
            if (dbManager.doesTableExist("BOOKING")) {
                return;
            }

            // SQL statement to create the BOOKING table
            String createBookingTableSQL = "CREATE TABLE BOOKING ("
                    + "BOOKING_ID VARCHAR(10) PRIMARY KEY, "
                    + "START_DATE DATE, "
                    + "END_DATE DATE, "
                    + "ROOM_ID VARCHAR(10), "
                    + "USERNAME VARCHAR(50), "
                    + "TOTAL_PRICE DOUBLE, "
                    + "HOTEL_ID VARCHAR(10), "
                    + "BOOKING_STATUS VARCHAR(20))";

            // Execute the table creation SQL
            statement.executeUpdate(createBookingTableSQL);
            System.out.println("BOOKING table created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating BOOKING table: " + e.getMessage());
        }
    }

    public boolean createBooking(String start, String end, Room room, User user, String hotelID) {
        // Validate room and user
        if (room == null || user == null) {
            System.out.println("No room or user data!");
            return false;
        }
        

        try {
            java.sql.Date startDate = convertToSqlDate(start);
            java.sql.Date endDate = convertToSqlDate(end);

            // Check if the room is available for the given dates
            if (room.isAvailable(startDate, endDate)) {
                room.setAvailabilityDate(end); // Update availability date
                room.setIsBooked(true); // Mark the room as booked
                String bookingID = idGenerator(); // Generate a new booking ID

                // Create a new Booking object to calculate the total cost
                Booking newBooking = new Booking(bookingID, startDate, endDate, room, user, hotelID);
                double calculatedPrice = newBooking.getTotalPrice();

                // Insert the new booking into the database
                String sqlBooking = String.format("INSERT INTO BOOKING (BOOKING_ID, START_DATE, END_DATE, ROOM_ID, USERNAME, TOTAL_PRICE, HOTEL_ID, BOOKING_STATUS) "
                        + "VALUES ('%s', '%s', '%s', '%s', '%s', %.2f, '%s', '%s')",
                        bookingID, startDate, endDate, room.getRoomID(), user.getUserName(), calculatedPrice, hotelID, "Booked");

                // SQL statement to update the ROOM table
                String sqlUpdateRoom = String.format("UPDATE ROOM SET AVAILABILITY_STATUS = 'Booked', DATE_FROM = '%s' WHERE ROOM_ID = '%s' AND HOTEL_ID = '%s'",
                        endDate, room.getRoomID(), hotelID);

                // Execute the SQL statements
                dbManager.updateDB(sqlBooking); // Insert new booking
                dbManager.updateDB(sqlUpdateRoom); // Update room status

                System.out.println("Booking '" + bookingID + "' created successfully with total price: " + calculatedPrice);
                return true;

            } else {
                System.out.println("Room is not available!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error creating new booking: " + e.getMessage());
        }

        return false; // Return false if the booking could not be created
    }

    // Method to get booking data
    public Booking getBookingData(String bookingID) {
        String query = "SELECT * FROM BOOKING WHERE BOOKING_ID = '" + bookingID + "'";
        try {
            ResultSet rs = dbManager.queryDB(query);
            if (rs.next()) {
                // Create a Booking object using the retrieved data
                return extractBookingFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving booking: " + e.getMessage());
        }
        return null;
    }
    
    public Booking getBookingByUser(String username) {
        String query = "SELECT * FROM BOOKING WHERE USERNAME = '" + username + "'";
        try {
            ResultSet rs = dbManager.queryDB(query);
            if (rs.next()) {
                // Create a Booking object using the retrieved data
                return extractBookingFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving booking: " + e.getMessage());
        }
        return null;
    }

    // Method to extract booking from result set
    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {

        String bookingID = rs.getString("BOOKING_ID");
        Date startDate = rs.getDate("START_DATE");
        Date endDate = rs.getDate("END_DATE");
        String roomID = rs.getString("ROOM_ID");
        String username = rs.getString("USERNAME");
        double totalPrice = rs.getDouble("TOTAL_PRICE");
        String hotelID = rs.getString("HOTEL_ID");
        String status = rs.getString("BOOKING_STATUS");

        // Retrieve Room and User objects as needed using roomID and username
        Room room = roomManager.getRoomData(roomID, hotelID);
        User user = userManager.getUserData(username);
        return new Booking(bookingID, startDate, endDate, room, user, totalPrice, hotelID, status);

    }

    //  Method to extened current booking
    public boolean extendBooking(String bookingID, String newEndDate) {
        Booking booking = getBookingData(bookingID);

        if ("cancelled".equals(booking.getStatus())) {
            return false;
        }

        Room room = booking.getRoom();
        String roomID = room.getRoomID();
        String hotelID = room.getHotelID();

        String sql = "UPDATE BOOKING SET END_DATE = '" + newEndDate + "', BOOKING_STATUS = 'active' WHERE BOOKING_ID = '" + bookingID + "'";
        dbManager.updateDB(sql);
        

        java.sql.Date checkOut = convertToSqlDate(newEndDate);

        String updateRoomSQL = String.format(
                "UPDATE ROOM SET DATE_FROM = '%s' "
                + "WHERE ROOM_ID = '%s' AND HOTEL_ID = '%s'",
                checkOut, roomID, hotelID
        );
        dbManager.updateDB(updateRoomSQL);
        System.out.printf("Booking %s extend succesfully\n", bookingID);
        return true;
    }

    // Method to cancel current booking
    public boolean cancelBooking(String bookingID) {
        Booking booking = getBookingData(bookingID);
       
        Room room = booking.getRoom();
        String roomID = room.getRoomID();
        String hotelID = room.getHotelID();

        // Set room as not booked
        room.setIsBooked(false);

        // Get today's date and convert it
        String today = dateFormat.format(room.getTodayDate());  // Use new Date() directly
        java.sql.Date todayDate = convertToSqlDate(today);
        room.setAvailabilityDate(today);

        // Update booking status
        String bookingSQL = "UPDATE BOOKING SET BOOKING_STATUS = 'cancelled' WHERE BOOKING_ID = '" + bookingID + "'";
        dbManager.updateDB(bookingSQL);
        booking.setStatus("cancelled");

        // Update room status - include HOTEL_ID in WHERE clause
        String updateRoomSQL = String.format(
                "UPDATE ROOM SET AVAILABILITY_STATUS = 'Available', DATE_FROM = '%s' "
                + "WHERE ROOM_ID = '%s' AND HOTEL_ID = '%s'",
                todayDate, roomID, hotelID
        );
        dbManager.updateDB(updateRoomSQL);

        System.out.printf("Booking %s cancelled successfully\n", bookingID);
        return true;

    }

    //Method to change current room
    public void changeRoom(String bookingID, Room newRoom) {

        // Fetching the booking data from the database
        Booking booking = this.getBookingData(bookingID);
        if (booking == null) {
            System.out.println("Booking ID does NOT exist");
        }

        if (newRoom == null) {
            System.out.println("New room is not available.");
            return;
        }

        // Freeing up the current room by setting room as available
        Room currentRoom = booking.getRoom();
        if (currentRoom != null) {
            String todayStr = dateFormat.format(booking.getEndDate());
            String updateCurrentRoomSQL = String.format(
                    "UPDATE FROM SET AVAILABILITY_STATUS = 'Available', DATE_FROM = '%s' WHERE ROMM_ID = '%s' AND HOTEL_ID = '%s'",
                    todayStr, currentRoom.getRoomID(), booking.getHotelID());
            dbManager.updateDB(updateCurrentRoomSQL);
        }

        // Assigning new room - seeting new room as booked
        if (newRoom != null) {
            String newEndDateStr = dateFormat.format(booking.getEndDate());
            String updateNewRoomSQL = String.format("UPDATE ROOM SET AVAILABILITY_STATUS = 'Booked', DATE_FROM = '%s' WHERE ROOM_ID = '%s' AND HOTEL_ID = '%s'",
                    newEndDateStr, newRoom.getRoomID(), booking.getBookingID());
            dbManager.updateDB(updateNewRoomSQL);
        }

        // Updating booking with new room ID
        String updateBookingSQL = String.format("UPDATE BOOKING SET ROOM_ID = '%s', WHERE BOOKING_ID = '%s'",
                newRoom.getRoomID(), bookingID);
        dbManager.updateDB(updateBookingSQL);
        booking.setRoom(newRoom);
        booking.calculateTotalCost();
    }

    // Method to generate a unique booking ID by incrementing a counter
    public String idGenerator() {
        String selectSQL = "SELECT MAX(BOOKING_ID) AS MAX_ID FROM BOOKING";
        try {
            ResultSet rs = dbManager.queryDB(selectSQL);

            if (rs.next()) {
                String maxId = rs.getString("MAX_ID");
                if (maxId != null) {
                    int nextId = Integer.parseInt(maxId.split("-")[1]) + 1;
                    return "BKG-" + nextId;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "BKG-1"; // Default to BK-1 if no entries exist or error occurs
    }

    public String viewBookingsByUser(String username) {
        StringBuilder bookingDetails = new StringBuilder();
        bookingDetails.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s\n",
                "Booking ID", "Start Date", "End Date", "Room ID", "Username",
                "Total Price", "Hotel ID", "Booking Status"));
        bookingDetails.append("====================================================\n");
        // Search booking using username
        String query = "SELECT * FROM BOOKING WHERE USERNAME = '" + username + "'";
        try {
            ResultSet rs = dbManager.queryDB(query);
            if (rs.next()) {
                String bookingID = rs.getString("BOOKING_ID");
                Date startDate = rs.getDate("START_DATE");
                Date endDate = rs.getDate("END_DATE");
                String roomID = rs.getString("ROOM_ID");
                double totalPrice = rs.getDouble("TOTAL_PRICE");
                String hotelID = rs.getString("HOTEL_ID");
                String bookingStatus = rs.getString("BOOKING_STATUS");

                // Append booking details to the StringBuilder
                bookingDetails.append(String.format("%s, %s, %s, %s, %s, %.2f, %s, %s\n",
                        bookingID,
                        (startDate != null ? startDate.toString() : "N/A"),
                        (endDate != null ? endDate.toString() : "N/A"),
                        roomID,
                        username,
                        totalPrice,
                        hotelID,
                        bookingStatus));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving booking: " + e.getMessage());
        }
        return bookingDetails.toString();
    }
    
    

    // Method to display an invoice for a specific booking
    public String displayInvoice(String bookingID) {
        try {
            Booking booking = this.getBookingData(bookingID);

            if (booking == null) {
                return "No booking found with ID: " + bookingID;
            }

            // Safely get room ID, handling null room case
            String roomID = "No Room Assigned";
            double roomPrice = 0.0;
            if (booking.getRoom() != null) {
                roomID = booking.getRoom().getRoomID();
                roomPrice = booking.getRoom().getPrice();
            }

            double totalPrice = booking.getTotalPrice();
            double gst = totalPrice * 0.15;
            double totalOwing = totalPrice + gst;

            // Build the invoice string with proper formatting and null checks
            StringBuilder invoiceText = new StringBuilder();
            invoiceText.append("====================\n");
            invoiceText.append("            INVOICE              \n");
            invoiceText.append("====================\n");
            invoiceText.append(String.format("%-15s: %s\n", "Booking ID", booking.getBookingID()));
            invoiceText.append(String.format("%-15s: %s\n", "Hotel ID", booking.getHotelID()));
            invoiceText.append(String.format("%-15s: %s\n", "Room ID", roomID));
            invoiceText.append(String.format("%-15s: %s\n", "Guest", booking.getUserName()));
            invoiceText.append(String.format("%-15s: %s\n", "Start Date", dateFormat.format(booking.getStartDate())));
            invoiceText.append(String.format("%-15s: %s\n", "End Date", dateFormat.format(booking.getEndDate())));
            invoiceText.append("--------------------\n");
            invoiceText.append(String.format("%-15s: $%.2f\n", "$Room Rate", roomPrice));
            invoiceText.append(String.format("%-15s: $%.2f\n", "$Subtotal", totalPrice));
            invoiceText.append(String.format("%-15s: $%.2f\n", "GST (15%)", gst));
            invoiceText.append("--------------------\n");
            invoiceText.append(String.format("%-15s: $%.2f\n", "$Total Owing", totalOwing));
            invoiceText.append(String.format("%-15s: %s\n", "Status", booking.getStatus()));
            invoiceText.append("====================\n");

            return invoiceText.toString();
        } catch (Exception e) {
            System.err.println("Error generating invoice: " + e.getMessage());
            return "Error generating invoice: " + e.getMessage();
        }
    }

    public void clearBookingData(String username) {
        try {
           
            if (dbManager.doesTableExist("BOOKING")) {
                dbManager.updateDB("DELETE FROM BOOKING WHERE USERNAME = '" + username + "'");
            }
        } catch (Exception e) {
            System.err.println("Error clearing booking data: " + e.getMessage());
        }
    }

    private java.sql.Date convertToSqlDate(String dateStr) {
        try {
            java.util.Date utilDate = dateFormat.parse(dateStr);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.err.println("Error coverting data");
            return null;
        }

    }

}
