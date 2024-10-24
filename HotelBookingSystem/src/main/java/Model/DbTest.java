package Model;

<<<<<<< HEAD
import java.sql.Date;

=======
>>>>>>> af3d5c98dbde58d8d45a799eb7a2a9d103999d63
public class DbTest {

    public static void main(String[] args) {
        
        HotelManager hm = new HotelManager();      
        hm.createHotelDB();
        hm.initializeHotels();
        hm.viewHotels();
        hm.createNewHotel("HTL-3", "Rotorua Lake", "Rotorua", 5, 3, 1);
        hm.updateHotelDetails("HTL-3", "Lake Shore", "Rotorua");

        UserManager um = new UserManager();
<<<<<<< HEAD
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
        

=======
        um.createDatabase();
        User newUser = new User("Mike_22", "12345", "Mike", "02153535", "example@gmail.com");
        
        newUser.setType(UserType.STAFF);
        um.registerUser(newUser);
        um.closeConnection();

>>>>>>> af3d5c98dbde58d8d45a799eb7a2a9d103999d63
    }

}
