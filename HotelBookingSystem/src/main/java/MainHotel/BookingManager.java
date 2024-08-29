/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author alex
 */
public class BookingManager implements FileHandler, ID {

    private String fileName;
    private Map<String, Booking> bookingData;
    private RoomManager roomManager;
    private UserManager userManager;

    private int bookingCount;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookingManager(String bookingFile, RoomManager roomManager, UserManager userManager) {
        this.fileName = bookingFile;
        this.bookingData = new HashMap<>();
        this.roomManager = roomManager;
        this.userManager = userManager;
        this.bookingCount = 0;
    }

    @Override
    public void loadData() {
        bookingData.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Booking booking = parseBookingData(line);
                if (booking != null) {
                    bookingData.put(booking.getBookingID(), booking);
                    int bookingID = Integer.parseInt(booking.getBookingID().split("-")[1]);
                    bookingCount = Math.max(bookingCount, bookingID);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    @Override
    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Booking ID,Start Date,End Date,Room ID,Username,Total Price,Hotel ID, Booking Status");
            for (Booking booking : bookingData.values()) {
                printWriter.println(dataToString(booking));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    private Booking parseBookingData(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        roomManager.loadData();
        userManager.loadData();
        String[] parts = line.split(",");
        Room room = roomManager.getRoomData(parts[3], parts[6]);
        User user = userManager.getUserData(parts[4]);
        if (room == null || user == null) {
            System.out.println("Room or User not found for data: " + line);
            return null;
        }
        try {
            Booking booking = new Booking(
                    parts[0],
                    dateFormat.parse(parts[1]),
                    dateFormat.parse(parts[2]),
                    room,
                    user,
                    Double.parseDouble(parts[5]),
                    parts[6],
                    parts[7]
            );
            return booking;

        } catch (ParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String dataToString(Booking booking) {
        String output;
        String startDateStr = dateFormat.format(booking.getStartDate());
        String endDateStr = dateFormat.format(booking.getEndDate());
        output = String.format(
                "%s,%s,%s,%s,%s,%.2f,%s,%s",
                booking.getBookingID(),
                startDateStr,
                endDateStr,
                booking.getRoomID(),
                booking.getUserName(),
                booking.getTotalPrice(),
                booking.getHotelID(),
                booking.getStatus()
        );
        return output;
    }

    public Booking createBooking(String start, String end, Room room, User user, String hotelID) {
        if (room == null || user == null) {
            System.out.println("No room or user data!");
            return null;
        }
        try {
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);
            if (room.isAvailable(startDate, endDate)) {
                room.setAvailabilityDate(end);
                room.setIsBooked(true);
                String bookingID = idGenerator(null);
                Booking booking = new Booking(bookingID, startDate, endDate, room, user, hotelID);
                this.bookingData.put(booking.getBookingID(), booking);
                return booking;
            }
            else{
                System.out.println("Room is not available!");
                return null;
            }

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd. Please Try again\n");
        }
        return null;
    }

    public void extendBooking(String bookingID, String newEndDateStr) {
        Booking booking = this.bookingData.get(bookingID);
        if (booking == null) {
            System.out.println("Booking ID does NOT exist");
            return;
        }
        if (booking != null) {
            try {
                Date newEndDate = dateFormat.parse(newEndDateStr);
                if (newEndDate.after(booking.getEndDate()) && !newEndDate.before(booking.getRoom().getAvailabilityDate())) {
                    booking.setEndDate(newEndDate);
                    booking.getRoom().setAvailabilityDate(newEndDateStr);
                    booking.calculateTotalCost();
                    System.out.println("Booking extend to "+ newEndDateStr +" successfully.\n");
                } else {
                    System.out.println("Room is not available.\n");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd. Please Try again\n");
            }
        } 
    }

    // Implementing cancelBooking function
    public void cancelBooking(String bookingID) {

        Booking booking = this.bookingData.get(bookingID);
        if (booking == null) {
            System.out.println("Booking ID does NOT exist");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("cancelled")) {
            System.out.println("Booking is already cancelled.\n");
            return;
        }

        Room room = booking.getRoom();

        if (booking.getBookingID().equalsIgnoreCase(bookingID)) {
            booking.setStatus("cancelled");
            if (room != null) {
                room.setIsBooked(false);
                String todayStr = dateFormat.format(new Date());
                room.setAvailabilityDate(todayStr);
            }
            System.out.println("Booking canceled successfully.\n");
        } else {
            System.out.println("Booking ID doesn't match.\n");
        }
    }

    // Implementing changeRoom function
    public void changeRoom(String bookingID, Room newRoom) {
        
        Booking booking = this.getBookingData(bookingID);
        if (booking == null) {
            System.out.println("Booking ID does NOT exist");
            return;
        }
        // Remove current room booking
        Room room = booking.getRoom();
        room.setIsBooked(false);
        String todayStr = dateFormat.format(new Date());
        room.setAvailabilityDate(todayStr);
        
        // Assigning new room and price
        booking.setRoom(newRoom);
        newRoom.setIsBooked(true);
        String newEndDateStr = dateFormat.format(booking.getEndDate());
        newRoom.setAvailabilityDate(newEndDateStr);
        booking.calculateTotalCost();
        System.out.println("Room changed successfully to Room ID: " + newRoom.getRoomID() + ".\n");
    }

    //Implementing printInvoice function
    public void printInvoice(String bookingID) {

        Booking booking = this.getBookingData(bookingID);

        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingID);
            return;
        }

        double gst = booking.getTotalPrice() * 0.15;
        double totalOwing = booking.getTotalPrice() + gst;

        System.out.println("<<< INVOICE >>>");
        System.out.printf("%-15s: %s%n", "Booking ID", booking.getBookingID());
        System.out.printf("%-15s: %s%n", "Hotel ID", booking.getHotelID());
        System.out.printf("%-15s: %s%n", "Room ID", booking.getRoomID());
        System.out.printf("%-15s: %s%n", "Start Date", booking.getStartDate());
        System.out.printf("%-15s: %s%n", "End Date", booking.getEndDate());
        System.out.printf("%-15s: $%.2f%n", "Total Price", booking.getTotalPrice());
        System.out.printf("%-15s: $%.2f%n", "Total GST", gst);
        System.out.printf("%-15s: $%.2f%n", "Total Owing", totalOwing);
    }

    @Override
    public String idGenerator(Object context) {
        this.bookingCount++;
        return "BKG-" + this.bookingCount;
    }

    public Booking getBookingData(String bookingID) {
        return this.bookingData.get(bookingID);
    }

    public Set<String> getAllBookingID() {
        return this.bookingData.keySet();
    }
    
    public Booking getBookingByUsername(String username){
        for(Booking booking : bookingData.values()){
            if(booking.getUserName().equals(username) && !booking.getStatus().equals("cancelled")){
                return booking;
            }
        }
        return null;
    }

}
