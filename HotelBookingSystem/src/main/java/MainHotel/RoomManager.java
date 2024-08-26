package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author minthihakoko
 */
public class RoomManager implements FileHandler {

    private String fileName;
    private Map<String, Room> roomData;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public RoomManager(String fileName) {
        this.fileName = fileName;
        this.roomData = new HashMap<>();
    }

    @Override
    public void loadData() {
        roomData.clear();
        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            bufferReader.readLine();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                Room room = parseRoomData(line);
                roomData.put(room.getRoomID(), room);
            }
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    @Override
    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("RoomID,RoomType,Price,AvailabilityStatus,AvailableFrom,HotelID");
            for(Room room : roomData.values()){
                printWriter.println(dataToString(room));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }

    }

    private Room parseRoomData(String line) {
        String[] parts = line.split(",");
        RoomType roomType = RoomType.valueOf(parts[1].toUpperCase());
        Room room = new Room(parts[0],roomType, Double.parseDouble(parts[2]), parts[5]);
        
        room.setAvailabilityDate(parts[4]);
        return room;
    }
    
    private String dataToString(Room room){
        String output;
        String availabilityDateStr = (room.getAvailabilityDate() != null) 
            ? dateFormat.format(room.getAvailabilityDate()) 
            : "Now";
        output = String.format(
                "%s,%s,%.2f,%s,%s,%s", 
                room.getRoomID(),
                room.getRoomType(),
                room.getPrice(),
                (room.isBooked()) ? "Booked" : "Available",
                availabilityDateStr,
                room.getHotelID()
                );
        return output;
    }
    
    public void addRoomData(Room newRoom){
        roomData.put(newRoom.getRoomID(), newRoom);
    }
    
    public Room getRoomData(String roomID){
        return roomData.get(roomID);
    }
    
    public boolean updateUserData(String roomID, Room newRoomData){
        if(!roomData.containsKey(roomID)){
            return false;
        }else{
            roomData.put(roomID, newRoomData);
            return true;  
        } 
    }
    
    public Set<String> getAllRoomID() {
        return roomData.keySet();
    }

}
