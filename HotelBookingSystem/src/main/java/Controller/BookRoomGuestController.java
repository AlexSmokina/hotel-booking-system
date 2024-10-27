/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.HotelManager;
import Model.RoomManager;
import Model.UserManager;
import View.BookRoomGuest;
import View.GuestMenu;
import View.RoomAvailability;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alex
 */
public class BookRoomGuestController implements ActionListener {

    BookRoomGuest view;
    BookingManager bookingManager;
    UserManager userManager;
    HotelManager hotelManager;
    RoomManager roomManager;

    private static final String DEFAULT_CHECK_IN = "Check-in date (yyyy-mm-dd)";
    private static final String DEFAULT_CHECK_OUT = "Check-out date (yyyy-mm-dd)";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookRoomGuestController(BookRoomGuest view) {
        this.view = view;
        bookingManager = BookingManager.getInstance();
        userManager = UserManager.getInstance();
        hotelManager = HotelManager.getInstance();
        roomManager = RoomManager.getInstance();
        initialise();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Check Availability".equals(command)) {
            handleBookRoom();
        } else if ("Return".equals(command)) {
            GuestMenu guestMenu = new GuestMenu();
            guestMenu.setVisible(true);
            updateHotelChoices();
            view.dispose();
        }
    }

    private void initialise() {
        view.getCheckAvailability().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
        updateHotelChoices();
    }

    private void updateHotelChoices() {
        // Get the ComboBox from the view
        JComboBox<String> hotelChoice = view.getHotelChoice();

        // Clear existing items
        hotelChoice.removeAllItems();

        // Get hotels from HotelManager
        List<String> hotels = hotelManager.getHotelNames();

        // Add each hotel to the ComboBox
        for (String hotel : hotels) {
            hotelChoice.addItem(hotel);
        }
    }
    
        private void handleBookRoom() {

        String checkIn = view.getCheckInDate().getText();
        String checkOut = view.getCheckOutDate().getText();
        String hotelName = view.getHotelChoice().getSelectedItem().toString();

        if (isDefaultOrEmpty(checkIn, DEFAULT_CHECK_IN) || isDefaultOrEmpty(checkOut, DEFAULT_CHECK_OUT)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidDateFormat(checkIn) || !isValidDateFormat(checkOut)) {
            JOptionPane.showMessageDialog(view,
                    "Please enter dates in the format: yyyy-MM-dd",
                    "Invalid Date Format",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        RoomAvailability roomAvailabilityPage = new RoomAvailability();
        roomAvailabilityPage.setVisible(true);
        String username = userManager.getCurrentUser().getUserName();
        new RoomAvailabilityController(roomAvailabilityPage, username, checkIn, checkOut, hotelName);
        view.dispose();

    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
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
