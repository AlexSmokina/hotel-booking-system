/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.BookingManager;
import Model.DbManager;
import Model.HotelManager;
import Model.RoomManager;
import Model.UserManager;
import View.StartMenu;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/**
 *
 * @author minthihakoko
 */
public class StartProgram {

    public static void main(String[] args) {
        setLookAndFeel();
        
        SwingUtilities.invokeLater(() -> {
            databaseSetUp();
            StartMenu start = new StartMenu();
            start.setVisible(true);
        });
        DbManager dbManager = DbManager.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (dbManager.getConnection() != null) {
                dbManager.closeConnections();
                shutdownDatabase();  // Properly shut down Derby database
            }
        }));
    }

    private static void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void databaseSetUp() {
        UserManager userManager = UserManager.getInstance();
        HotelManager hotelManager = HotelManager.getInstance();
        RoomManager roomManager = RoomManager.getInstance();
        BookingManager bookingManager = BookingManager.getInstance();
        userManager.createDatabase();
        hotelManager.createDatabase();
        roomManager.createDatabase();
        bookingManager.createDatabase();
        
        hotelManager.insertInitialData();
        roomManager.insertInitialData();
    }
    
    private static void shutdownDatabase() {
        try {
            // Shutdown Derby explicitly
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
            System.out.println("Derby database shut down successfully.");
        } catch (SQLException ex) {
            if ("XJ015".equals(ex.getSQLState())) {
                System.out.println("Derby shutdown confirmed.");
            } else {
                ex.printStackTrace();
            }
        }
    }
    
}
