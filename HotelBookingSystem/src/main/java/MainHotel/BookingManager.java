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
    private UserManager userManger;

    private int bookingCount;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookingManager(String bookingFile, RoomManager roomManager, UserManager userManger) {
        this.fileName = bookingFile;
        this.bookingData = new HashMap<>();
        this.roomManager = roomManager;
        this.userManger = userManger;
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
            printWriter.println("Booking ID,Start Date,End Date,Room ID,Username,Total Price,Hotel ID");
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
        userManger.loadData();
        String[] parts = line.split(",");
        Room room = roomManager.getRoomData(parts[3],parts[6]);
        User user = userManger.getUserData(parts[4]);
        if (room == null || user == null) {
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
                    parts[6]);
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
                "%s,%s,%s,%s,%s,%.2f,%s",
                booking.getBookingID(),
                startDateStr,
                endDateStr,
                booking.getRoomID(),
                booking.getUserName(),
                booking.getTotalPrice(),
                booking.getHotelID()
        );
        return output;
    }

    public void creatBooking(String start, String end, Room room, User user, String hotelID) {
        if(room == null || user == null ){
            System.out.println("No room or user data!");
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
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void extendBooking(String bookingID, String newEndDateStr){
        Booking booking = this.bookingData.get(bookingID);
        if(booking!=null){
            try {
                Date newEndDate = dateFormat.parse(newEndDateStr);
                if(newEndDate.after(booking.getEndDate()) && !newEndDate.before(booking.getRoom().getAvailabilityDate())){
                    booking.setEndDate(newEndDate);
                    booking.getRoom().setAvailabilityDate(newEndDateStr);
                    booking.calculateTotalCost();
                }
                else{
                    System.out.println("Room is not available.\n");
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Wrong Booking ID");
        }
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

}
