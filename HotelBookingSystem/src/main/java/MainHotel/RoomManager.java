package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            for (Room room : roomData.values()) {
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
        Room room = new Room(parts[0], roomType, Double.parseDouble(parts[2]), parts[5]);

        room.setAvailabilityDate(parts[4]);
        return room;
    }

    private String dataToString(Room room) {
        String output;
        String availabilityDateStr = dateFormat.format(room.getAvailabilityDate());
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

    public void createRoom(Room newRoom) {
        roomData.put(newRoom.getRoomID(), newRoom);
    }

    public Room getRoomData(String roomID) {
        return roomData.get(roomID);
    }

    public boolean updateRoomData(String roomID, Room newRoomData) {
        if (!roomData.containsKey(roomID)) {
            return false;
        } else {
            roomData.put(roomID, newRoomData);
            return true;
        }
    }

    public List<Room> filterRoomByHotel(String hotelID) {
        List<Room> roomList = new ArrayList<>();
        for (Room room : roomData.values()) {
            if (room.getHotelID().equalsIgnoreCase(hotelID)) {
                roomList.add(room);
            }
        }
        return roomList;
    }

    public List<Room> filerByDate(String date, String hotelID) {
        List<Room> roomList = new ArrayList<>();
        try {
            Date startDate = dateFormat.parse(date);
            
            for (Room room : roomData.values()) {
                if (room.getAvailabilityDate().equals(startDate) && room.getHotelID().equalsIgnoreCase(hotelID)) {
                    roomList.add(room);
                }
            }    
        } catch (ParseException e) {
            System.out.println(e);
        }
        return roomList;

    }

    public Set<String> getAllRoomID() {
        return roomData.keySet();
    }

}
