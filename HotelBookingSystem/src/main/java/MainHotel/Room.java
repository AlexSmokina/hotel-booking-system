/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Room implements ID {

    private String roomID;
    private String roomType;
    private double price;
    private boolean isBooked;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.roomID = idGenerator();
        this.price = price;
        this.isBooked = false;
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

    @Override
    public String toString() {
        return "Room Type: " + this.getRoomType() + "\nRoom ID: " + this.roomID + "\nPrice: $" + this.getPrice() + "\nAvailability Status: " + this.isBooked + "\n";
    }

}
