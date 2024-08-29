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
 * @author Alexander Smokina & Min Thiha Ko Ko 
 */
public class RoomManager implements FileHandler, ID {

    private String fileName;
    private Map<String, Room> roomData;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Map<String, Integer> idCounters;

    public RoomManager(String fileName) {
        this.fileName = fileName;
        this.roomData = new HashMap<>();
        this.idCounters = new HashMap<>();
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
                if (room != null) {
                    String key = room.getHotelID() + "_" + room.getRoomID();
                    roomData.put(key, room);

                    String keyCount = room.getHotelID() + "_" + room.getRoomType();
                    int roomID = Integer.parseInt(room.getRoomID().split("-")[1]);
                    int maxNumber = idCounters.getOrDefault(keyCount, 0);
                    idCounters.put(keyCount, Math.max(maxNumber, roomID));
                }
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
            printWriter.println("Room ID,Room Type,Price,Availability Status,Available From,Hotel ID");
            for (Room room : roomData.values()) {
                printWriter.println(dataToString(room));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }

    }

    private Room parseRoomData(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");
        RoomType roomType = RoomType.valueOf(parts[1].toUpperCase());
        Room room = new Room(parts[0], roomType, parts[5]);
        if (parts[3].equals("Booked")) {
            room.setIsBooked(true);
        }
        room.setAvailabilityDate(parts[4]);
        return room;
    }

    public String dataToString(Room room) {
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

    public void createRoom(String roomType, String hotelID) {
        RoomType type = RoomType.valueOf(roomType.toUpperCase());
        String roomID = idGenerator(new Object[]{hotelID, type});
        Room newRoom = new Room(roomID, type, hotelID);
        String key = newRoom.getHotelID() + "_" + newRoom.getRoomID();
        roomData.put(key, newRoom);
    }

    @Override
    public final String idGenerator(Object context) {
        if (!(context instanceof Object[])) {
            throw new IllegalArgumentException("Context must be an array with RoomType and HotelID.");
        }
        Object[] contextArray = (Object[]) context;
        String hotelID = (String) contextArray[0];
        RoomType roomType = (RoomType) contextArray[1];

        String key = hotelID + "_" + roomType.name();
        idCounters.putIfAbsent(key, 0);
        int currentID = idCounters.get(key) + 1;
        idCounters.put(key, currentID);
        String output = "RM";
        switch (roomType) {
            case SUITE:
                output += "/SU-";
                break;
            case PREMIUM:
                output += "/PRM-";
                break;
            default:
                output += "/STD-";
                break;
        }
        return output + currentID;
    }

    public Room getRoomData(String roomID, String hotelID) {
        String key = hotelID + "_" + roomID;
        return roomData.get(key);
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

    public Map<String, Room> getAllRooms() {
        return this.roomData;
    }

}
