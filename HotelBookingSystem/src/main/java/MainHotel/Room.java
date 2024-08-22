/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alex
 */
public class Room implements ID {

    private String roomID;
    private String roomType;
    private double price;
    private boolean isBooked;
    private Date availabilityDate;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.roomID = idGenerator();
        this.price = price;
        this.isBooked = false;
        this.availabilityDate = null;
    }

    // Checking if the room is not booked or if the requested period is entirely after the current availability date
    public boolean isAvailable(Date startDate, Date endDate) {
        return !isBooked || (availabilityDate != null && startDate.after(availabilityDate) && endDate.after(availabilityDate));
    }

    @Override
    public final String idGenerator() {
        String output = "RM";
        if (this.roomType.equalsIgnoreCase("suit")) {
            output += "/S-";
        } else if (this.roomType.equalsIgnoreCase("first class")) {
            output += "/FC-";
        } else {
            output += "/E-";
        }
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Date getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(String availabilityDateStr) {
        try {
            this.availabilityDate = dateFormat.parse(availabilityDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Room Type: " + this.getRoomType() + "\n"
                + "Room ID: " + this.roomID + "\n"
                + "Price: $" + this.getPrice() + "\n"
                + "Availability Status: " + (this.isBooked ? "Booked" : "Available") + "\n"
                + "Available From: " + (this.availabilityDate != null ? dateFormat.format(this.availabilityDate) : "Now") + "\n";
    }

}
