/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The Hotel class represents a hotel within the Hotel Booking System.
 * It includes information such as the hotel's name, ID, location, and the number of different room types available.
 * This class also interacts with the RoomManager to manage room-related operations.
 */
public class Hotel {

    private String name;
    private String hotelID;
    private String location;
    private int numStandardRooms;
    private int numPremiumRooms;
    private int numSuites;
    
    private RoomManager rm;

    public Hotel(String hotelID, String name, String location) {
        this.hotelID = hotelID;
        this.name = name;
        this.location = location;
        this.numStandardRooms = 0;
        this.numPremiumRooms = 0;
        this.numSuites = 0;
        
    }
    
    // Provides a string representation of the hotel's details, including its ID, name, location,
    // and the number of rooms of each type (standard, premium, suite).
    @Override
    public String toString() {
        return  "Hotel ID: " + getHotelID() + "\n" +
                "Hotel Name: " + getName() + "\n"+
                "Location: " + getLocation() + "\n"+
                "Standard: "+ getNumStandardRooms() + "\n"+
                "Premium: " + getNumPremiumRooms() + "\n" +
                "Suite: " + getNumSuites() + "\n";
                 
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumStandardRooms() {
        return numStandardRooms;
    }

    public void setNumStandardRooms(int numStandardRooms) {
        this.numStandardRooms = numStandardRooms;
    }

    public int getNumPremiumRooms() {
        return numPremiumRooms;
    }

    public void setNumPremiumRooms(int numPremiumRooms) {
        this.numPremiumRooms = numPremiumRooms;
    }

    public int getNumSuites() {
        return numSuites;
    }

    public void setNumSuites(int numSuites) {
        this.numSuites = numSuites;
    }

    public RoomManager getRm() {
        return rm;
    }

    public void setRm(RoomManager rm) {
        this.rm = rm;
    }

}
