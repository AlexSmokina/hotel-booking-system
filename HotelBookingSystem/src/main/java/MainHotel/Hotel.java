/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author alex
 */
public class Hotel implements ID {

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

    

//    public void addRoom(Room room) {
//        rooms.add(room);
//        numRooms++;
//    }
//
//    public void removeRoom(String roomID) {
//        Iterator<Room> iterator = rooms.iterator();
//        boolean found = false;
//        while (iterator.hasNext()) {
//            Room room = iterator.next();
//            if (room.getRoomID().equalsIgnoreCase(roomID)) {
//                iterator.remove();
//                numRooms -= 1;
//                System.out.println("Room ID " + roomID + " is successfully deleted.");
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            System.out.println("Room ID isn't valid.");
//        }
//    }
//
//    public List<Room> getAvailableRooms() {
//        List<Room> availableRooms = new ArrayList<>();
//        for (Room room : rooms) {
//            if (!room.isBooked()) {
//                availableRooms.add(room);
//            }
//        }
//        return availableRooms;
//    }
//
//    public void printAllBookings() {
//        for (Booking booking : bookings) {
//            System.out.println(booking);
//        }
//    }
//
//    public void printBooking(Date date) {
//        for (Booking booking : bookings) {
//            if (!booking.getStartDate().after(date) && !booking.getEndDate().before(date)) {
//                System.out.println(booking);
//            }
//        }
//    }
//
//    public Booking searchBooking(String bookingID) {
//        for (Booking booking : bookings) {
//            if (booking.getBookingID().equalsIgnoreCase(bookingID)) {
//                return booking;
//            }
//        }
//        return null;
//    }
    @Override
    public String toString() {
        return  "Hotel ID: " + getHotelID() + "\n" +
                "Hotel Name: " + getName() + "\n"+
                "Location: " + getLocation() + "\n"+
                "Standard: "+ getNumStandardRooms() + "\n"+
                "Premium: " + getNumPremiumRooms() + "\n" +
                "Suite: " + getNumSuites() + "\n";
                 
    }

    @Override
    public String idGenerator(Object context) {
        String output = "HTL-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

    // Getters and Setters

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the hotelID
     */
    public String getHotelID() {
        return hotelID;
    }

    /**
     * @param hotelID the hotelID to set
     */
    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the numStandardRooms
     */
    public int getNumStandardRooms() {
        return numStandardRooms;
    }

    /**
     * @param numStandardRooms the numStandardRooms to set
     */
    public void setNumStandardRooms(int numStandardRooms) {
        this.numStandardRooms = numStandardRooms;
    }

    /**
     * @return the numPremiumRooms
     */
    public int getNumPremiumRooms() {
        return numPremiumRooms;
    }

    /**
     * @param numPremiumRooms the numPremiumRooms to set
     */
    public void setNumPremiumRooms(int numPremiumRooms) {
        this.numPremiumRooms = numPremiumRooms;
    }

    /**
     * @return the numSuites
     */
    public int getNumSuites() {
        return numSuites;
    }

    /**
     * @param numSuites the numSuites to set
     */
    public void setNumSuites(int numSuites) {
        this.numSuites = numSuites;
    }

    /**
     * @return the rm
     */
    public RoomManager getRm() {
        return rm;
    }

    /**
     * @param rm the rm to set
     */
    public void setRm(RoomManager rm) {
        this.rm = rm;
    }


}
