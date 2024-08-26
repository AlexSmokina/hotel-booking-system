/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alex
 */
public class BookingManager implements FileHandler {

    private String fileName;
    private Map<String, Booking> bookingData;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookingManager(String fileName) {
        this.fileName = fileName;
        this.bookingData = new HashMap<>();
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
                bookingData.put(booking.getBookingID(), booking);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    private Booking parseBookingData(String line) {
        String[] parts = line.split(",");
        String bookingID = parts[0];
        
        //Booking booking = new Booking(bookingID, );
    }

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
