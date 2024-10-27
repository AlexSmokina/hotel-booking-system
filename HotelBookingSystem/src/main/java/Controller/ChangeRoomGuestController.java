/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Booking;
import Model.BookingManager;
import Model.UserManager;
import View.ChangeRoomAvailability;
import View.ChangeRoomGuest;
import View.GuestMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class ChangeRoomGuestController implements ActionListener {

    ChangeRoomGuest view;
    UserManager userManager;
    BookingManager bookingManager;

    public ChangeRoomGuestController(ChangeRoomGuest view) {
        this.view = view;
        this.userManager = UserManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getReturnPreviousMenu().addActionListener(this);
        view.getCheckRoomAvailability().addActionListener(this);
        handleBookingData();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Check Available Room".equals(command)) {
            handleCheckRooms();
        } else if ("Return".equals(command)) {
            GuestMenu guestMenuPage = new GuestMenu();
            guestMenuPage.setVisible(true);
            view.dispose();
        }
    }

    private void handleBookingData() {
        String username = userManager.getCurrentUser().getUserName();
        String bookingDetail = bookingManager.viewBookingsByUser(username);
        view.getBookingTextArea().setText(bookingDetail);
    }

    private void handleCheckRooms() {
        String bookingID = view.getEnterBookingID().getText();
        Booking booking = bookingManager.getBookingData(bookingID);
        if (booking == null || booking.getStatus() == "cancelled") {
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
