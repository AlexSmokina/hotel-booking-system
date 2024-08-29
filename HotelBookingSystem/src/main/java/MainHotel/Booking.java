/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alex
 */
public class Booking{

    private String bookingID;
    private Date startDate;
    private Date endDate;
    private Room room;
    private User user;
    private double totalPrice;
    private String hotelID;
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public Booking(String bookingID, Date startDate, Date endDate, Room room, User user, String hotelID) {
        this.bookingID = bookingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.user = user;
        calculateTotalCost();
        this.hotelID = hotelID;  
    }
    
    
    public Booking(String bookingID, Date startDate, Date endDate, Room room, User user, double totalPrice, String hotelID) {
        this.bookingID = bookingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.user = user;
        this.totalPrice = totalPrice;
        this.hotelID = hotelID;  
    }
    
    public final void calculateTotalCost() {
        long diffInMillis = this.getEndDate().getTime() - this.getStartDate().getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        this.totalPrice = diffInDays * this.room.getPrice();
        
    }

//    public void cancelBooking(String bookingID) {
//        if (this.getStatus().equalsIgnoreCase("canceled")) {
//            System.out.println("Booking is already canceled.\n");
//            return;
//        }
//        if (this.bookingID.equalsIgnoreCase(bookingID)) {
//            this.status = "canceled";
//            if (this.room != null) {
//                this.room.setIsBooked(false);
//            }
//            System.out.println("Booking canceled successfully.\n");
//        } else {
//            System.out.println("Booking ID doesn't match.\n");
//        }
//    }
//
//    public void extendBooking(String newEndDateStr) {
//
//        try {
//            Date newEndDate = dateFormat.parse(newEndDateStr);
//
//            if (newEndDate.after(this.endDate) && this.room.getAvailabilityDate().equals(this.endDate)) {
//                this.endDate = newEndDate;
//                this.room.setAvailabilityDate(newEndDateStr);
//                // Recalculating the total price
//                this.totalPrice = calculateTotalCost();
//
//                System.out.println("Booking extended successfully to " + newEndDate + ".\n");
//            } else {
//                System.out.println("Room is not available.\n");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void changeRoom(Room newRoom) {
//        // Checking if the current room is booked
//        if (!this.room.isBooked()) {
//            System.out.println("The current room is NOT booked at all");
//            return;
//        }
//        // Checking if the new room is already booked
//        if (newRoom.isBooked()) {
//            System.out.println("The new room ID " + newRoom.getRoomID() + " is already booked");
//            System.out.println("Swapping is NOT possible. Try another room\n");
//            return;
//        }
//        // Releasing current room
//        this.room.setIsBooked(false);
//        // Assigning new room and price
//        this.setRoom(newRoom);
//        this.room.setIsBooked(true);
//        this.totalPrice = calculateTotalCost();
//        System.out.println("Room changed successfully to Room ID: " + newRoom.getRoomID() + ".\n");
//    }


      // Implement Invoice Class
//    public void printInvoice() {
//        double gst = this.getTotalPrice() * 0.15;
//        System.out.println("<<< INVOICE >>>");
//        System.out.println("Booking ID " + this.getBookingID() + ":\n"
//                + "|Hotel ID: " + this.hotelID + "|\n"
//                + "|Room ID: " + getRoom().getRoomID() + "|\n"
//                + "|Start Date: " + this.getStartDate() + "|\n"
//                + "|End Date: " + this.getEndDate() + "|\n"
//                + "|Total Price: $" + this.getTotalPrice() + "|\n"
//                + "|Total GST: $" + gst + "|\n"
//                + "|Total Owing: $" + (this.getTotalPrice() + gst) + "|\n");
//    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Booking ID: ").append(this.getBookingID()).append("\n");
        output.append("Start Date: ").append(dateFormat.format(this.getStartDate())).append("\n");
        output.append("End Date: ").append(dateFormat.format(this.getEndDate())).append("\n");
        output.append("Room ID: ").append(this.getRoomID()).append("\n");
        output.append("Total Price: ").append(this.getTotalPrice()).append("\n");
        output.append("Guest username: ").append(this.getUserName()).append("\n");
        output.append("Hotel ID: ").append(this.getHotelID()).append("\n");
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
    
    public Room getRoom(){
        return this.room;
    }
    
    public String getRoomID(){
        return this.room.getRoomID();
    }
    
    public String getUserName(){
        return this.user.getUserName();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getHotelID() {
        return hotelID;
    }

    
}
