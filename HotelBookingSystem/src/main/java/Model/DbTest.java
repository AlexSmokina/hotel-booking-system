package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DbTest {

    public static void main(String[] args) {
        
        RoomManager rm = new RoomManager();
        rm.createDatabase();
        rm.createRoom("STANDARD", "HTL-1");
        rm.createRoom("PREMIUM", "HTL-1");
        
        Room standardRoom = rm.getRoomData("RM/STD-1", "HTL-1");
        if (standardRoom != null) {
            System.out.println("Room found: " + standardRoom);
        } else {
            System.out.println("Room not found.");
        }
        
        standardRoom.setIsBooked(true);
        boolean isUpdated = rm.updateRoomData("RM/STD-1", standardRoom);
        if (isUpdated) {
            System.out.println("Room updated successfully.");
        } else {
            System.out.println("Failed to update room.");
        }
        
        List<Room> roomsByHotel = rm.filterRoomByHotel("HTL-1");
        if (!roomsByHotel.isEmpty()) {
            System.out.println("Rooms found in hotel HTL-1: " + roomsByHotel.size());
            for (Room room : roomsByHotel) {
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms found in hotel HTL-1.");
        }
        
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<Room> roomsByDate = rm.filterByDate(today, "HTL-1");
        if (!roomsByDate.isEmpty()) {
            System.out.println("Rooms available from today in hotel HTL-1: " + roomsByDate.size());
            for (Room room : roomsByDate) {
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms available from today in hotel HTL-1.");
        }
        
        List<String> roomIDs = rm.getAllRoomIDs();
        if (!roomIDs.isEmpty()) {
            System.out.println("Room IDs found: " + roomIDs.size());
            for (String id : roomIDs) {
                System.out.println("Room ID: " + id);
            }
        } else {
            System.out.println("No room IDs found.");
        }
        
        List<Room> allRooms = rm.getAllRooms();
        if (!allRooms.isEmpty()) {
            System.out.println("Total rooms found: " + allRooms.size());
            for (Room room : allRooms) {
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms found.");
        }

//        HotelManager hm = new HotelManager();
//        hm.createDatabase();
//        hm.insertInitialData();
//        hm.viewHotels();
//        hm.createNewHotel("HTL-3", "Rotorua Lake", "Rotorua", 5, 3, 1);
//        hm.updateHotelDetails("HTL-3", "Lake Shore", "Rotorua");

//        UserManager um = new UserManager();
//        User user = um.signIn("Mike_22", "12345");
//        if (user == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("true");
//        }
//        um.createDatabase();
//        User newUser = new User("Mike_22", "12345", "Mike", "02153535", "example@gmail.com");
//        newUser.setType(UserType.STAFF);
//        um.registerUser(newUser);
//        um.closeConnection();
//        BookingDB bm = new BookingDB();       
//        bm.createBookingDB();
//        Date startDate = Date.valueOf("2024-10-01");
//        Date endDate = Date.valueOf("2024-10-03");
//        bm.createBooking(
//                "BKG-1",
//                startDate,
//                endDate,
//                "RM/STD-2",
//                "mike_22", 
//                room,
//                "HTL-1",
//                "active"
//        );
    }
}
