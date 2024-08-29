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
public class Room {


    private String roomID;
    private RoomType roomType;
    private double price;
    private boolean isBooked;
    private Date availabilityDate;
    private String hotelID;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Room(String roomID, RoomType roomType, String hotelID) {
        this.roomType = roomType;
        this.roomID = roomID;
        this.price = roomType.getPrice();
        this.isBooked = false;
        this.availabilityDate = getTodayDate();
        this.hotelID = hotelID;
    }

    // Checking if the room is not booked or if the requested period is entirely after the current availability date
    public boolean isAvailable(Date startDate, Date endDate) {
        return !isBooked || (availabilityDate != null && startDate.after(availabilityDate) && endDate.after(availabilityDate));
    }

    

    public Date getTodayDate() {
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
        return roomType.name();
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
