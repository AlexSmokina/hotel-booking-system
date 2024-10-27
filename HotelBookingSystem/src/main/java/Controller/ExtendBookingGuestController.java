/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import View.ExtendBookingGuest;
import View.GuestMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class ExtendBookingGuestController implements ActionListener {

    ExtendBookingGuest view;
    BookingManager bookingManager;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ExtendBookingGuestController(ExtendBookingGuest view) {
        this.view = view;
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getConfirmExtend().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Confirm".equals(command)) {
            handleExtendBooking();
        } else if ("Return".equals(command)) {
            GuestMenu guestMenuPage = new GuestMenu();
            guestMenuPage.setVisible(true);
            view.dispose();
        }
    }

    private void handleExtendBooking() {
        String bookingID = view.getBookingID().getText();
        if (bookingManager.getBookingData(bookingID) == null) {
            JOptionPane.showMessageDialog(view,
                    "Booking does not exist",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String checkOut = view.getCheckOutDate().getText();
        if (!isValidDateFormat(checkOut)) {
            JOptionPane.showMessageDialog(view,
                    "Please enter dates in the format: yyyy-MM-dd",
                    "Invalid Date Format",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = bookingManager.extendBooking(bookingID, checkOut);
        if (success) {
            JOptionPane.showMessageDialog(view,
                    "Booking extended successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            GuestMenu guestMenuPage = new GuestMenu();
            guestMenuPage.setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Failled to extend booking, This booking has been cancelled",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean isValidDateFormat(String date) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);  // Parse to check format validity
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
