package DataBase;

import java.sql.ResultSet;

public class DbTest {

    public static void main(String[] args) {

        HotelDB hotelDB = new HotelDB();
        hotelDB.createHotelDB();
        hotelDB.initializeHotels();
        hotelDB.createNewHotel("HTL-3", "Rotorua Lake", "Rotorua", 5, 7, 3);   
        hotelDB.createNewHotel("HTL-4", "Ocean View Hotel", "Wellington", 6, 5, 1);
        hotelDB.updateHotelDetails("HTL-3", "Lake Shore", "Rotorua");
        hotelDB.viewHotels();
        hotelDB.getHotel("HTL-3");
    }

}
