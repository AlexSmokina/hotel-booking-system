/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Hotel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The HotelManager class is responsible for managing hotel data, including
 * creating new hotels, updating hotel information, loading and saving data to files,
 * and handling room initialization. It utilizes OOP principles such as abstraction,
 * encapsulation, inheritance, and polymorphism.
 */
public class HotelManager implements FileHandler, ID {

    private String fileName;
    private Map<String, Hotel> hotelData;
    private int hotelCount;
    private RoomManager roomManager;

    // Constructor for initialising HotelManager with a file name and RoomManager instance.
    public HotelManager(String hotelFile, RoomManager roomManager) {
        this.fileName = hotelFile;
        this.hotelData = new HashMap<>();
        this.roomManager = roomManager;
        this.hotelCount = 0;
    }

    // Loading hotel data from the file and populates the hotelData map.
    @Override
    public void loadData() {
        hotelData.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Hotel hotel = parseHotelData(line);
                if (hotel != null) {
                    hotelData.put(hotel.getHotelID(), hotel);
                    int hotelID = Integer.parseInt(hotel.getHotelID().split("-")[1]);
                    this.hotelCount = Math.max(hotelCount, hotelID);
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    // Saving the current hotel data to the file.
    @Override
    public void saveData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println("Hotel ID,Hotel Name,Hotel Address,Standard,Premium,Suite");
            for (Hotel hotel : hotelData.values()) {
                printWriter.println(dataToString(hotel));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    // Parsing line of hotel data from the file and returns a Hotel object.
    private Hotel parseHotelData(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");

        Hotel hotel = new Hotel(parts[0], parts[1], parts[2]);
        hotel.setNumStandardRooms(Integer.parseInt(parts[3]));
        hotel.setNumPremiumRooms(Integer.parseInt(parts[4]));
        hotel.setNumSuites(Integer.parseInt(parts[5]));
        return hotel;
    }

    // Converting hotel data to a string format for saving to the file.
    public String dataToString(Hotel hotel) {
        String output;
        output = String.format(
                "%s,%s,%s,%d,%d,%d",
                hotel.getHotelID(),
                hotel.getName(),
                hotel.getLocation(),
                hotel.getNumStandardRooms(),
                hotel.getNumPremiumRooms(),
                hotel.getNumSuites()
        );
        return output;
    }

    // Creating a new hotel with the specified details and initializes its rooms.
    public void createNewHotel(String name, String location, int numStandardRooms, int numPremiumRooms, int numSuites) {
        String hotelID = idGenerator(null);
        Hotel newHotel = new Hotel(hotelID, name, location);
        this.initializeRooms(newHotel, numStandardRooms, numPremiumRooms, numSuites);
        hotelData.put(newHotel.getHotelID(), newHotel);
    }

    // Initialising rooms for a hotel based on the number of standard, premium, and suite rooms.
    private void initializeRooms(Hotel hotel, int numStandardRooms, int numPremiumRooms, int numSuites) {
        hotel.setNumStandardRooms(numStandardRooms);
        hotel.setNumPremiumRooms(numPremiumRooms);
        hotel.setNumSuites(numSuites);

        for (int i = 0; i < numStandardRooms; i++) {
            roomManager.createRoom("STANDARD", hotel.getHotelID());

        }
        for (int i = 0; i < numPremiumRooms; i++) {
            roomManager.createRoom("PREMIUM", hotel.getHotelID());

        }
        for (int i = 0; i < numSuites; i++) {
            roomManager.createRoom("SUITE", hotel.getHotelID());

        }
        roomManager.saveData();
    }

    // Searching for a hotel by its name and returns the matching Hotel object.
    public Hotel searchHotel(String hotelName) {
        for (Hotel hotel : hotelData.values()) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return hotel;
            }
        }
        return null;
    }
    
    public Map<String, Hotel> getAllHotels() {
        return this.hotelData;
    }

    // Generating new unique hotel ID.
    @Override
    public String idGenerator(Object context) {
        this.hotelCount++;
        return "HTL-" + this.hotelCount;
    }

    public Hotel getHotelData(String hotelID) {
        return hotelData.get(hotelID);
    }

    // Updating hotel data with new information and returns true if successful.
    public boolean updateHotelData(String hotelID, Hotel newHotelData) {
        if (!hotelData.containsKey(hotelID)) {
            return false;
        } else {
            hotelData.put(hotelID, newHotelData);
            return true;
        }
    }
    
    // Checking if there are any hotels in the system.
    public boolean isEmpty(){
        return hotelData.isEmpty();
    }

}
