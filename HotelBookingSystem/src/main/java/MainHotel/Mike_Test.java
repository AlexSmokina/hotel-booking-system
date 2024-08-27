/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author minthihakoko
 */
public class Mike_Test {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
//        String file = "./database/user.csv";
//        UserManger um = new UserManger(file);
//        um.loadData();
//
//        System.out.println("Loaded Users:");
//        for (String username : um.getAllUsernames()) {
//            User user = um.getUserData(username);
//            System.out.println(user);
//        }
//
//        User newUser = new User("mike_10", "1234", "Mike", "0212231313", "mike@example.com");
//        newUser.setType(UserType.GUEST);
//        um.addUserData(newUser);
//
//        for (String username : um.getAllUsernames()) {
//            User user = um.getUserData(username);
//            System.out.println(user);
//        }
//        um.saveData();

//        System.out.println("Loaded Room Data:");
//        for (String roomID : rm.getAllRoomID()) {
//            Room room = rm.getRoomData(roomID);
//            System.out.println(room);
//        }
//        Room newRoom = new Room(RoomType.STANDARD, 150.00, "HTL-0102"); 
//        newRoom.setAvailabilityDate("2024-08-22"); 
        //rm.createRoom(newRoom);
//        System.out.println("After adding new one:");
//        for (String roomID : rm.getAllRoomID()) {
//            Room room = rm.getRoomData(roomID);
//            System.out.println(room);
//        }
//        String filePath = "./database/room.csv";
//
//        RoomManager rm = new RoomManager(filePath);
//
//        rm.loadData();
//
//        System.out.println("---------");
//        System.out.println("Available Rooms");
//        String hotelID = "HTL-0101";
//        String startDate = "2024-08-26";
//
//        List<Room> rooms = rm.filerByDate(startDate, hotelID);
//        for (Room room : rooms) {
//            System.out.println(room);
//        }

        //rm.saveData();
        String userFile = "./database/user.csv";
        String roomFile = "./database/room.csv";
        String bookingFile = "./database/booking.csv";
        
        BookingManager bm = new BookingManager(bookingFile,roomFile,userFile);
        

        bm.loadData();
        

        
//        String hotelID = "HTL-0101";
//        String startDate = "2024-08-26";
//        
//        System.out.println("Available Rooms");
//        List<Room> rooms = rm.filerByDate(startDate, hotelID);
//        for (Room room : rooms) {
//            System.out.println(room);
//        }
        
        System.out.println("Load bookings from file");
        for(String bookingID : bm.getAllBookingID()){
            Booking booking = bm.getBookingData(bookingID);
            System.out.println(booking);
        }
    }
}
