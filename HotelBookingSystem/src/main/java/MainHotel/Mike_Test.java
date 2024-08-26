/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author minthihakoko
 */
public class Mike_Test {

    public static void main(String[] args) {
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
        
        String filePath = "./database/room.csv";
        

        RoomManager rm = new RoomManager(filePath);
        

        rm.loadData();
        

        System.out.println("Loaded Room Data:");
        for (String roomID : rm.getAllRoomID()) {
            Room room = rm.getRoomData(roomID);
            System.out.println(room);
        }
        
        Room newRoom = new Room(RoomType.STANDARD, 150.00, "HTL-0101"); 
        newRoom.setAvailabilityDate("2024-08-22"); 
        
        rm.addRoomData(newRoom);
        System.out.println("After adding new one:");
        for (String roomID : rm.getAllRoomID()) {
            Room room = rm.getRoomData(roomID);
            System.out.println(room);
        }
        
        rm.saveData();
        
    }
}
