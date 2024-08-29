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
public class HotelManager implements FileHandler, ID {

    private String fileName;
    private Map<String, Hotel> hotelData;
    private int hotelCount;
    private RoomManager roomManager;

    public HotelManager(String hotelFile, RoomManager roomManager) {
        this.fileName = hotelFile;
        this.hotelData = new HashMap<>();
        this.roomManager = roomManager;
        this.hotelCount = 0;
    }

    @Override
    public void loadData() {
        hotelData.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Hotel hotel = parseHotelData(line);
                if(hotel!=null){
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
    
    private String dataToString(Hotel hotel) {
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

    public void createNewHotel(String name, String location, int numStandardRooms, int numPremiumRooms, int numSuites) {
        String hotelID = idGenerator(null);
        Hotel newHotel = new Hotel(hotelID, name, location);
        this.initializeRooms(newHotel, numStandardRooms, numPremiumRooms, numSuites);
        hotelData.put(newHotel.getHotelID(), newHotel);
    }
    
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
    
    public Hotel searchHotel(String hotelName){
        for(Hotel hotel : hotelData.values()){
            if(hotel.getName().equalsIgnoreCase(hotelName)){
                return hotel;
            }
        }
        return null;
    }
    
    @Override
    public String idGenerator(Object context) {
        this.hotelCount++;
        return "HTL-" + this.hotelCount;
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
