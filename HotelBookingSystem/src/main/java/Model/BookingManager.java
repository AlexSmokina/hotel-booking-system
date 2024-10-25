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
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;

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

    // Constructor to initialize the database connection
    public BookingManager() {
        dbManager = new DbManager();
        conn = dbManager.getConnection();
        roomManager = new RoomManager();
        userManager = UserManager.getInstance();
    }

    // Method to create the Booking table in the database
    @Override
    public void createDatabase() {
        try {
            statement = conn.createStatement();
            // Drop the BOOKING table if it exists
            if (dbManager.doesTableExist("BOOKING")) {
                statement.executeUpdate("DROP TABLE BOOKING");
                System.out.println("Existing BOOKING table dropped.");
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

    // Method to new booking record in the database
    public void createBooking(String bookingID, Date startDate, Date endDate, String roomID, String username, Room room, User user, String hotelID, String status) {

        // Create a new Booking object to calculate the total cost
        Booking newBooking = new Booking(bookingID, startDate, endDate, room, user, hotelID);
        double calculatedPrice = newBooking.getTotalPrice();

        // Insert the new booking into the database using the calculated price
        String sql = String.format("INSERT INTO BOOKING (BOOKING_ID, START_DATE, END_DATE, ROOM_ID, USERNAME, TOTAL_PRICE, HOTEL_ID, BOOKING_STATUS) "
                + "VALUES ('%s', '%s', '%s',' %s', '%s', %.2f, '%s', '%s')",
                bookingID, startDate, endDate, roomID, username, calculatedPrice, hotelID, status);

        try {
            dbManager.updateDB(sql);
            System.out.println("Booking '" + bookingID + "' created successfully with total price: " + calculatedPrice);
        } catch (Exception e) {
            System.out.println("Error creating new booking: " + e.getMessage());
        }
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
    public void extendBooking(String bookingID, String newEndDate) {

        String sql = "UPDATE BOOKING SET END_DATE = '" + newEndDate + "', BOOKING_STATUS = 'active' WHERE BOOKING_ID = '" + bookingID + "'";
        dbManager.updateDB(sql);
        System.out.printf("Booking %s extend succesfully\n", bookingID);
    }

    // Method to cancel current booking
    public void cancelBooking(String bookingID) {
        String sql = "UPDATE BOOKING SET BOOKING_STATUS = 'cancelled' WHERE BOOKING_ID = '" + bookingID + "'";
        dbManager.updateDB(sql);
        System.out.printf("Booking %s cancelled succesfully\n", bookingID);
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

    // Method to display an invoice for a specific booking
    public void displayInvoice(String bookingID, JTextArea invoiceTextArea) {
        try {
            Booking booking = this.getBookingData(bookingID);
            if (booking == null) {
                invoiceTextArea.setText("No booking found with ID: " + bookingID);
                return;
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
            invoiceText.append("=================================\n");
            invoiceText.append("            INVOICE              \n");
            invoiceText.append("=================================\n");
            invoiceText.append(String.format("%-15s: %s\n", "Booking ID", booking.getBookingID()));
            invoiceText.append(String.format("%-15s: %s\n", "Hotel ID", booking.getHotelID()));
            invoiceText.append(String.format("%-15s: %s\n", "Room ID", roomID));
            invoiceText.append(String.format("%-15s: %s\n", "Guest", booking.getUserName()));
            invoiceText.append(String.format("%-15s: %s\n", "Start Date", dateFormat.format(booking.getStartDate())));
            invoiceText.append(String.format("%-15s: %s\n", "End Date", dateFormat.format(booking.getEndDate())));
            invoiceText.append("---------------------------------\n");
            invoiceText.append(String.format("%-15s: $%.2f\n", "Room Rate", roomPrice));
            invoiceText.append(String.format("%-15s: $%.2f\n", "Subtotal", totalPrice));
            invoiceText.append(String.format("%-15s: $%.2f\n", "GST (15%)", gst));
            invoiceText.append("---------------------------------\n");
            invoiceText.append(String.format("%-15s: $%.2f\n", "Total Owing", totalOwing));
            invoiceText.append(String.format("%-15s: %s\n", "Status", booking.getStatus()));
            invoiceText.append("=================================\n");

            // Display the invoice in the JTextArea
            invoiceTextArea.setText(invoiceText.toString());

        } catch (Exception e) {
            invoiceTextArea.setText("Error generating invoice: " + e.getMessage());
            System.err.println("Error generating invoice: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
