/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alex
 */
public class BookingDB {

    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;

    // Constructor to initialize the database connection
    public BookingDB() {
        dbManager = new DbManager();
        conn = dbManager.getConnection();
    }

    // Method to create the Booking table in the database
    public void createBookingDB() {
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

}
