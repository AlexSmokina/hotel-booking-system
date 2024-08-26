/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author alex
 */
public class HotelManager implements FileHandler {

    private String fileName;
    private Map<String, Hotel> hotelData;

    public HotelManager(String fileName) {
        this.fileName = fileName;
        hotelData = new HashMap<>();
    }

    @Override
    public void loadData() {
        hotelData.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                Hotel hotel = parseHotelData(line);
                hotelData.put(hotel.getHotelID(), hotel);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    private Hotel parseHotelData(String line) {
        String[] parts = line.split(",");
        int numRooms = Integer.parseInt(parts[2]);
        Hotel hotel = new Hotel(parts[0], parts[1], numRooms);
        return hotel;
    }

    @Override
    public void saveData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println("Hotel Name,Hotel ID,Hotel Address,Number of Rooms");
            for (Hotel hotel : hotelData.values()) {
                printWriter.println(String.format("%s,%s,%s,%d",
                        hotel.getName(), hotel.getHotelID(), hotel.getLocation(), hotel.getNumRooms()));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    public void addHotelData(Hotel newHotel) {
        hotelData.put(newHotel.getHotelID(), newHotel);
    }

    public Hotel getHotelData(String hotelID) {
        return hotelData.get(hotelID);
    }

    public boolean updateHotelData(String hotelID, Hotel newHotelData) {
        if (!hotelData.containsKey(hotelID)) {
            return false;
        } else {
            hotelData.put(hotelID, newHotelData);
            return true;
        }
    }

}
