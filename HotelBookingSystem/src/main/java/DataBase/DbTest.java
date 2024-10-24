package DataBase;

import java.sql.ResultSet;

public class DbTest {

    public static void main(String[] args) {

        HotelDB hotelDB = new HotelDB();
        hotelDB.createHotelDB();
        hotelDB.createNewHotel("htl-3", "Rotorua Lake", "Rotorua", 5, 7, 3);   
        hotelDB.createNewHotel("htl-4", "Ocean View Hotel", "Wellington", 6, 5, 1);
        hotelDB.updateHotelDetails("htl-3", "Lake Shore", "Rotorua");
    }

}
