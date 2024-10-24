package DataBase;

import Model.Hotel;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        } catch (SQLException ex) {
            Logger.getLogger(HotelDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initializeHotels() {
        try {
            String insertHotelSQL = "INSERT INTO HOTEL VALUES "
                    + "('HTL-1', 'Auckland Skyline', 'Auckland', 5, 3, 2), "
                    + "('HTL-2', 'Queenstown Grand', 'Queenstown', 4, 4, 1) ";

            dbManager.updateDB(insertHotelSQL);

            System.out.println("HOTEL data inserted succesfully");

        } catch (Exception e) {
            System.out.println("Error inserting initial HOTEL data: " + e.getMessage());
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
            System.out.println("Hotel ID: '" + hotelID + "' updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating hotel: " + e.getMessage());
        }
    }

    public void viewHotels() {
        String userQuery = "SELECT * FROM HOTEL";
        ResultSet rs = dbManager.queryDB(userQuery);

        try {
            while (rs.next()) {
                String hotelID = rs.getString("HOTELID");
                String hotelName = rs.getString("HOTEL_NAME");
                String hotelLocation = rs.getString("HOTEL_LOCATION");
                int standardRooms = rs.getInt("STANDARD");
                int premiumRooms = rs.getInt("PREMIUM");
                int suites = rs.getInt("SUITE");

                System.out.println("Hotel ID: " + hotelID + ", Name: " + hotelName
                        + ", Location: " + hotelLocation
                        + ", Standard Rooms: " + standardRooms
                        + ", Premium Rooms: " + premiumRooms
                        + ", Suites: " + suites);

            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(HotelDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getHotel(String hotelID) {
        Hotel hotel = null;
        String hotelQuery = "SELECT * FROM HOTEL WHERE HOTELID = '" + hotelID + "'";

        try {
            ResultSet rs = dbManager.queryDB(hotelQuery);

            if (rs.next()) {
                String id = rs.getString("HOTELID");
                String name = rs.getString("HOTEL_NAME");
                String location = rs.getString("HOTEL_LOCATION");
                int standardRooms = rs.getInt("STANDARD");
                int premiumRooms = rs.getInt("PREMIUM");
                int suites = rs.getInt("SUITE");

                hotel = new Hotel(id, name, location);
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(HotelDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
