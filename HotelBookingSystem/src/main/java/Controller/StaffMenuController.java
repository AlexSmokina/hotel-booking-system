/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import View.BookingManagement;
import View.HotelManagement;
import View.RoomManagement;
import View.StaffMenu;
import View.StartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class StaffMenuController implements ActionListener{
    private StaffMenu view;
    
    
    public StaffMenuController(StaffMenu view){
        this.view = view;
        initialise();
    }
    
    
    private void initialise(){
        view.getLogoutStaffMenuButton().addActionListener(this);
        view.getBookingManagementButton().addActionListener(this);
        view.getRoomManagementButton().addActionListener(this);
        view.getHotelManagementButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Logout".equals(command)) {
            
            StartMenu startPage = new StartMenu();
            startPage.setVisible(true);
            view.dispose();
            
        } else if ("Hotel Management".equals(command)) {
            HotelManagement hotelManagementPage = new HotelManagement();
            hotelManagementPage.setVisible(true);
            view.dispose();
            
        } else if("Room Management".equals(command)) {
            RoomManagement roomManagementPage = new RoomManagement();
            roomManagementPage.setVisible(true);
            view.dispose();
            
        } else if("Booking Management".equals(command)) {
            BookingManagement bookingManagementPage = new BookingManagement();
            bookingManagementPage.setVisible(true);
            view.dispose();
        }
    }
}
