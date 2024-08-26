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
    private int numRooms;
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(String name, String location, int numRooms) {
        this.name = name;
        this.location = location;
        this.numRooms = numRooms;
        this.hotelID = idGenerator();
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        numRooms++;
    }

    public void removeRoom(String roomID) {
        Iterator<Room> iterator = rooms.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if(room.getRoomID().equalsIgnoreCase(roomID)) {
                iterator.remove();
                numRooms -= 1;
                System.out.println("Room ID " + hotelID + " is successfully deleted.");
                found = true;
                break;
            }
        } 
        if(!found) {
            System.out.println("Room ID isn't valid.");
        }
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void printAllBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void printBooking(Date date) {
        for (Booking booking : bookings) {
            if (!booking.getStartDate().after(date) && !booking.getEndDate().before(date)) {
                System.out.println(booking);
            }
        }
    }

    public Booking searchBooking(String bookingID) {
        for (Booking booking : bookings) {
            if (booking.getBookingID().equalsIgnoreCase(bookingID)) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hotel Name: " + name + "\nHotel ID: " + hotelID
                + "\nLocation: " + location + "\nNumber of Rooms: " + numRooms;
    }

    @Override
    public String idGenerator() {
        String output = "HTL-";
        int id = (int) (Math.random() * 1001);
        return output + id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}