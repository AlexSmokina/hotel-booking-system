/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.UserManager;
import View.StaffMenu;
import View.ViewBookingGuest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alex
 */
public class ViewBookingGuestController implements ActionListener {

    ViewBookingGuest view;
    UserManager user;
    BookingManager booking;
    
    public ViewBookingGuestController (ViewBookingGuest view) {
        this.view = view;
        this.user = UserManager.getInstance();
        this.booking = BookingManager.getInstance();
        initialise();
    }
    
    private void initialise() {
        view.getReturnPreviousMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Return".equals(command)) {
             StaffMenu staffMenu = new StaffMenu();
             staffMenu.setVisible(true);
             view.dispose();
        }
    }

    
}
