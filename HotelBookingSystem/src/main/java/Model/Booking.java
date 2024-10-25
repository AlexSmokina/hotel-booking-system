/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alexander Smokina & Min Thiha Ko Ko
 *
 * The Booking class represents a reservation made by a user for a specific room
 * in a hotel. It includes details such as the booking ID, start and end dates,
 * the room reserved, the user who made the booking, the total price of the
 * stay, the associated hotel, and the status of the booking.
 */
public class Booking {

    private String bookingID;
    private Date startDate;
    private Date endDate;
    private Room room;
    private User user;
    private double totalPrice;
    private String hotelID;
    private String status;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor to initialize a new booking with the necessary details.
    public Booking(String bookingID, Date startDate, Date endDate, Room room, User user, String hotelID) {
        this.bookingID = bookingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.user = user;
        this.calculateTotalCost();
        this.hotelID = hotelID;
        this.status = "active";
    }

    // Constructor used for loading bookings from a file.
    // Includes all booking details, including total price and status.
    public Booking(String bookingID, Date startDate, Date endDate, Room room, User user, double totalPrice, String hotelID, String status) {
        this.bookingID = bookingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.user = user;
        this.totalPrice = totalPrice;
        this.hotelID = hotelID;
        this.status = status;
    }

    // Calculating the total cost of the booking based on the duration of the stay
    // (in days) and the price of the room per day.
    public final void calculateTotalCost() {
        long diffInMillis = this.getEndDate().getTime() - this.getStartDate().getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        this.totalPrice = diffInDays * this.room.getPrice();

    }

    // Returning String representation of the booking, including all relevant details.
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Booking ID: ").append(this.getBookingID()).append("\n");
        output.append("Start Date: ").append(dateFormat.format(this.getStartDate())).append("\n");
        output.append("End Date: ").append(dateFormat.format(this.getEndDate())).append("\n");

        // Check if room is null before accessing room details
        if (this.room != null) {
            output.append("Room ID: ").append(this.getRoomID()).append("\n");
        } else {
            output.append("Room ID: Not Assigned\n");
        }

        output.append("Total Price: ").append(this.getTotalPrice()).append("\n");
        output.append("Guest username: ").append(this.getUserName()).append("\n");
        output.append("Hotel ID: ").append(this.getHotelID()).append("\n");
        output.append("Booking Status: ").append(this.getStatus()).append("\n");
        return output.toString();
    }

    public String getBookingID() {
        return bookingID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getRoomID() {
        return this.room.getRoomID();
    }

    public String getUserName() {
        return this.user.getUserName();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getHotelID() {
        return hotelID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
