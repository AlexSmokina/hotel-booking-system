/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alex
 */
public class Room implements ID {

    private static final Map<String, Integer> counters = new HashMap<>();

    private String roomID;
    private RoomType roomType;
    private double price;
    private boolean isBooked;
    private Date availabilityDate;
    private String hotelID;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Room(RoomType roomType, double price, String hotelID) {
        this.roomType = roomType;
        this.roomID = idGenerator();
        this.price = price;
        this.isBooked = false;
        this.availabilityDate = getTodayDate();
        this.hotelID = hotelID;

    }

    //Constructor for Parsing Data from file.
    public Room(String roomID, RoomType roomType, double price, String hotelID) {
        this.roomType = roomType;
        this.roomID = roomID;
        this.price = price;
        this.isBooked = false;
        this.availabilityDate = getTodayDate();
        this.hotelID = hotelID;
    }

    // Checking if the room is not booked or if the requested period is entirely after the current availability date
    public boolean isAvailable(Date startDate, Date endDate) {
        return !isBooked || (availabilityDate != null && startDate.after(availabilityDate) && endDate.after(availabilityDate));
    }

    @Override
    public final String idGenerator() {
        counters.putIfAbsent(this.roomType.name(), 0);
        int currentID = counters.get(this.roomType.name()) + 1;
        counters.put(this.roomType.name(), currentID);
        String output = "RM";
        if (this.roomType == roomType.SUITE) {
            output += "/SU-";
        } else if (this.roomType == roomType.PREMIUM) {
            output += "/PRM-";
        } else {
            output += "/STD-";
        }
        return output + currentID;
    }

    private Date getTodayDate() {
        String todayStr = dateFormat.format(new Date());
        try {
            return dateFormat.parse(todayStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle exception as needed
        }
    }
    public String getRoomID() {
        return roomID;
    }

    public String getRoomType() {
        return roomType.toString();
    }

    public void setRoomType(RoomType roomType) {
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

    public String getHotelID() {
        return hotelID;
    }

    public void setAvailabilityDate(String availabilityDateStr) {
        try {
            this.availabilityDate = dateFormat.parse(availabilityDateStr);
            if (!this.availabilityDate.equals(getTodayDate())) {
                this.setIsBooked(true);
            }
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
                + "Available From: " + dateFormat.format(this.availabilityDate) + "\n"
                + "Hotel ID: " + this.getHotelID()+"\n";
    }

}
