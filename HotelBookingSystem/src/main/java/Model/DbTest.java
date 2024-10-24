package Model;

import java.sql.Date;

public class DbTest {

    public static void main(String[] args) {
        
        HotelManager hm = new HotelManager();      
        hm.createHotelDB();
        hm.initializeHotels();
        hm.viewHotels();
        hm.createNewHotel("HTL-3", "Rotorua Lake", "Rotorua", 5, 3, 1);
        hm.updateHotelDetails("HTL-3", "Lake Shore", "Rotorua");

        UserManager um = new UserManager();
        User user = um.signIn("Mike_22", "12345");
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println("true");
        }

        um.closeConnection();

        BookingDB bm = new BookingDB();
        
        bm.createBookingDB();

        Date startDate = Date.valueOf("2024-10-01");
        Date endDate = Date.valueOf("2024-10-03");

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
