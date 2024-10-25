package Model;

import java.sql.Date;
import javax.swing.JTextArea;

public class DbTest {

    public static void main(String[] args) {

        // RoomManager TESTS
        RoomManager rm = new RoomManager();
        rm.createDatabase();
        rm.createRoom("STANDARD", "HTL-1");
        Room standardRoom = rm.getRoomData("RM/STD-1", "HTL-1");
        if (standardRoom != null) {
            System.out.println("Room found: " + standardRoom);
        }

        // UserManager TESTS
        UserManager um = UserManager.getInstance();
        um.createDatabase();
        User newUser = new User("Mike_22", "12345", "Mike", "02153535", "example@gmail.com");
        newUser.setType(UserType.STAFF);
        um.registerUser(newUser);

        BookingManager bm = new BookingManager();
        bm.createDatabase();

        // BookingManager TETS
        testCreateBookingDB(bm);
        testCreateBooking(bm, rm, "RM/STD-1", "HTL-1", newUser);
        testGetBookingData(bm);
        testExtendBooking(bm);
        testCancelBooking(bm);

        Room newRoom = rm.getRoomData("RM/STD-2", "HTL-1");
        testChangeRoom(bm, rm, "RM/STD-2", "HTL-1");

        JTextArea invoiceTextArea = new JTextArea();
        testPrintInvoice(bm, invoiceTextArea);
    }

    // BookingManager tests functions
    public static void testCreateBookingDB(BookingManager bm) {
        bm.createDatabase();
        System.out.println("Booking database created successfully.");
    }

    public static void testCreateBooking(BookingManager bm, RoomManager roomManager, String roomID, String hotelID, User user) {
        Room room = roomManager.getRoomData(roomID, hotelID);
        if (room == null) {
            System.out.println("Room not found for Room ID: " + roomID + " and Hotel ID: " + hotelID);
        } else {
            Date startDate = Date.valueOf("2024-10-01");
            Date endDate = Date.valueOf("2024-10-05");
            bm.createBooking("BKG-1", startDate, endDate, room.getRoomID(), user.getUserName(), room, user, room.getHotelID(), "active");
            System.out.println("Booking created successfully.");
        }
    }

    public static void testGetBookingData(BookingManager bm) {
        Booking booking = bm.getBookingData("BKG-1");
        if (booking != null) {
            System.out.println("Booking found: " + booking);
        } else {
            System.out.println("Booking not found.");
        }
    }

    public static void testExtendBooking(BookingManager bm) {
        bm.extendBooking("BKG-1", "2024-10-10");
        System.out.println("Booking extended successfully.");
    }

    public static void testCancelBooking(BookingManager bm) {
        bm.cancelBooking("BKG-1");
        System.out.println("Booking cancelled successfully.");
    }

    public static void testChangeRoom(BookingManager bm, RoomManager roomManager, String newRoomID, String hotelID) {
        Room newRoom = roomManager.getRoomData(newRoomID, hotelID);
        if (newRoom == null) {
            System.out.println("Room not found for Room ID: " + newRoomID + " and Hotel ID: " + hotelID);
        } else {
            bm.changeRoom("BKG-1", newRoom);
            System.out.println("Room changed successfully.");
        }
    }

    public static void testPrintInvoice(BookingManager bm, JTextArea textArea) {
        bm.displayInvoice("BKG-1", textArea);
        System.out.println(textArea.getText());
    }
}
 