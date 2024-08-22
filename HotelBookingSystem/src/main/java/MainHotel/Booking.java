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
public class Booking implements ID {

    private Room room;
    private String bookingID;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private String status;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Booking() {
        this.bookingID = idGenerator();
        this.status = "active";

    }

    public void createBooking(Room room, String startDateStr, String endDateStr) {
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            if (room.isAvailable(startDate, endDate)) {
                this.setRoom(room);
                this.startDate = startDate;
                this.endDate = endDate;
                this.room.setAvailabilityDate(endDateStr);
                this.room.setIsBooked(true);
                this.totalPrice = calculateTotalCost();
            } else {
                System.out.println("Room is not available for the selected dates.\n");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalCost() {
        long diffInMillis = this.endDate.getTime() - this.startDate.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        return diffInDays * room.getPrice();
    }

    public void cancelBooking(String bookingID) {
        if (this.getStatus().equalsIgnoreCase("canceled")) {
            System.out.println("Booking is already canceled.\n");
            return;
        }
        if (this.bookingID.equalsIgnoreCase(bookingID)) {
            this.status = "canceled";
            if (this.room != null) {
                this.room.setIsBooked(false);
            }
            System.out.println("Booking canceled successfully.\n");
        } else {
            System.out.println("Booking ID doesn't match.\n");
        }
    }

    public void extendBooking(String newEndDateStr) {

        try {
            Date newEndDate = dateFormat.parse(newEndDateStr);

            if (newEndDate.after(this.endDate) && this.room.getAvailabilityDate().equals(this.endDate)) {
                this.endDate = newEndDate;
                this.room.setAvailabilityDate(newEndDateStr);
                // Recalculating the total price
                this.totalPrice = calculateTotalCost();

                System.out.println("Booking extended successfully to " + newEndDate + ".\n");
            } else {
                System.out.println("Room is not available.\n");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void changeRoom(Room newRoom) {
        // Checking if the current room is booked
        if (!this.room.isBooked()) {
            System.out.println("The current room is NOT booked at all");
            return;
        }
        // Checking if the new room is already booked
        if (newRoom.isBooked()) {
            System.out.println("The new room ID " + newRoom.getRoomID() + " is already booked");
            System.out.println("Swapping is NOT possible. Try another room\n");
            return;
        }
        // Releasing current room
        this.room.setIsBooked(false);
        // Assigning new room and price
        this.setRoom(newRoom);
        this.room.setIsBooked(true);
        this.totalPrice = calculateTotalCost();
        System.out.println("Room changed successfully to Room ID: " + newRoom.getRoomID() + ".\n");
    }

    @Override
    public String idGenerator() {
        String output = "BKG-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

    public void printInvoice() {
        System.out.println("<<< INVOICE >>>");
        System.out.println("Booking ID " + this.getBookingID() + ":\n"
                + "|Room ID: " + getRoom().getRoomID() + "|\n"
                + "|Start Date: " + this.getStartDate() + "|\n"
                + "|End Date: " + this.getEndDate() + "|\n"
                + "|Total Price: $" + this.getTotalPrice() + "|\n");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("Booking ID: ").append(this.getBookingID()).append("\n");

        if (this.getRoom() != null) {
            output.append("Room ID: ").append(getRoom().getRoomID()).append("\n");
            output.append("Room Type: ").append(getRoom().getRoomType()).append("\n");
            output.append("Room Price per Night: $").append(getRoom().getPrice()).append("\n");
        } else {
            output.append("Room: Not yet assigned\n");
        }

        if (this.getStartDate() != null) {
            output.append("Start Date: ").append(this.getStartDate()).append("\n");
        } else {
            output.append("Start Date: Not yet set\n");
        }

        if (this.getEndDate() != null) {
            output.append("End Date: ").append(this.getEndDate()).append("\n");
        } else {
            output.append("End Date: Not yet set\n");
        }
        return output.toString();
    }

}
