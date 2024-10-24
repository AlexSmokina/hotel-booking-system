package DataBase;

import Model.Hotel;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelDB {

    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;

    public HotelDB() {
        dbManager = new DbManager();
        conn = dbManager.getConnection();
    }

    public void createHotelDB() {
        try {
            statement = conn.createStatement();
            if (dbManager.doesTableExist("HOTEL")) {
                statement.executeUpdate("DROP TABLE HOTEL");
            }
            String createHotelTableSQL = "CREATE TABLE HOTEL ("
                    + "HOTELID VARCHAR(10), "
                    + "HOTEL_NAME VARCHAR(50), "
                    + "HOTEL_LOCATION VARCHAR(20), "
                    + "STANDARD INT, "
                    + "PREMIUM INT, "
                    + "SUITE INT)";
            dbManager.updateDB(createHotelTableSQL);

            String insertHotelSQL = "INSERT INTO HOTEL VALUES "
                    + "('HTL-1', 'Auckland Skyline', 'Auckland', 2, 2, 1), "
                    + "('HTL-2', 'Queenstown Grand', 'Queenstown', 1, 1, 1) ";

            dbManager.updateDB(insertHotelSQL);

            System.out.println("HOTEL table created and data inserted.");

        } catch (SQLException ex) {
            Logger.getLogger(HotelDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createNewHotel(String hotelID, String name, String location, int numStandardRooms, int numPremiumRooms, int Suite) {
        
        String sql = "INSERT INTO HOTEL (HOTELID, HOTEL_NAME, HOTEL_LOCATION, STANDARD, PREMIUM, SUITE) "
               + "VALUES ('" + hotelID + "', '" + name + "', '" + location + "', " + numStandardRooms + ", " + numPremiumRooms + ", " + Suite + ")";
        
        try {
            dbManager.updateDB(sql);
            System.out.println("Hotel '" + name + "' added successfully.");
        } catch (Exception e) {
            System.out.println("Error creating new hotel: " + e.getMessage());
        }
    }
    
    public void updateHotelDetails(String hotelID, String name, String location) {
        
        String sql = "UPDATE HOTEL SET "
               + "HOTEL_NAME = '" + name + "', "
               + "HOTEL_LOCATION = '" + location + "'"
               + "WHERE HOTELID = '" + hotelID + "'";
        
        try {
            dbManager.updateDB(sql);
            System.out.println("Hotel ID: '"+ hotelID + "' updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating hotel: " + e.getMessage());
        }
                
                
        
    }

}
