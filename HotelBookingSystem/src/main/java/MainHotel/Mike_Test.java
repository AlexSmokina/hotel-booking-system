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

    public static void main(String[] args) {
        String userFile = "./database/user.csv";
        String roomFile = "./database/room.csv";
        String bookingFile = "./database/booking.csv";
        String hotelFile = "./database/hotel.csv";

        UserManager um = new UserManager(userFile);
        RoomManager rm = new RoomManager(roomFile);
        BookingManager bm = new BookingManager(bookingFile, rm, um);
        HotelManager hm = new HotelManager(hotelFile, rm);

        um.loadData();
        rm.loadData();
        bm.loadData();
        hm.loadData();

        //hm.createNewHotel("Grand Hotel", "Auckland", 2, 1, 1);
        //hm.createNewHotel("Nice Hotel", "Queenstown", 1, 1, 0);
        Room room = rm.getRoomData("RM/STD-1", "HTL-1");
        User user = um.getUserData("mike22");

        //bm.createBooking("2024-08-29", "2024-09-03", room, user, "HTL-1");
        bm.cancelBooking("BKG-1");

        

        //bm.printInvoice("BKG-1");
        bm.saveData();
        hm.saveData();
        rm.saveData();

    }
}
