/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.UserManager;
import View.GuestMenu;
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
    BookingManager bookingManager;

    public ViewBookingGuestController(ViewBookingGuest view) {
        this.view = view;
        this.user = UserManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }

    private void initialise() {
        String bookingDetails = bookingManager.viewBookingsByUser(user.getCurrentUser().getUserName());
        view.getBookingViewArea().setText(bookingDetails);
        view.getReturnPreviousMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Return".equals(command)) {
            GuestMenu guestMenu = new GuestMenu();
            guestMenu.setVisible(true);
            view.dispose();
        }
    }

}
