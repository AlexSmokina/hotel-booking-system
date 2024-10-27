/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Booking;
import Model.BookingManager;
import Model.UserManager;
import View.BookingManagement;
import View.ChangeRoomAvailability;
import View.ChangeRoomStaff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class ChangeRoomStaffController implements ActionListener{
    ChangeRoomStaff view;
    UserManager userManager;
    BookingManager bookingManager;

    public ChangeRoomStaffController(ChangeRoomStaff view) {
        this.view = view;
        this.userManager = UserManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }
    
    private void initialise() {
        view.getReturnPreviousMenu().addActionListener(this);
        view.getSearch().addActionListener(this);
        view.getCheckAvailabilityRoom().addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Check Available Room".equals(command)){
            handleCheckRooms();
            
        } else if ("Search".equals(command)){
            handleSearch();
        } else if ("Return".equals(command)){
            BookingManagement bookingManagement = new BookingManagement();
            bookingManagement.setVisible(true);
            view.dispose();
        }
    }

    private void handleSearch() {
        String username = view.getEnterGuestUsername().getText();
        if (userManager.getUserData(username) == null) {
            JOptionPane.showMessageDialog(view,
                    "Username does not exist",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bookingDetail = bookingManager.viewBookingsByUser(username);
        view.getBookingTextArea().setText(bookingDetail);
    }

    private void handleCheckRooms() {
        String bookingID = view.getEnterBookingID().getText();
        Booking booking = bookingManager.getBookingData(bookingID);
        if (booking == null || booking.getStatus()=="cancelled") {
            JOptionPane.showMessageDialog(view,
                    "Booking does not exist",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ChangeRoomAvailability changeRoomAvailabilityPage = new ChangeRoomAvailability();
        changeRoomAvailabilityPage.setVisible(true);
        new ChangeRoomAvailabilityController(changeRoomAvailabilityPage, bookingID);
        view.dispose();
    }

    
    
    
}
