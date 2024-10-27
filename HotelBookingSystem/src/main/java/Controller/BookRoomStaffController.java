/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BookRoomStaff;
import Model.BookingManager;
import Model.HotelManager;
import Model.RoomManager;
import Model.User;
import Model.UserManager;
import Model.UserType;
import View.BookingManagement;
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
 * @author minthihakoko
 */
public class BookRoomStaffController implements ActionListener {

    BookRoomStaff view;
    BookingManager bookingManger;
    HotelManager hotelManager;
    UserManager userManager;
    RoomManager roomManager;

    private static final String DEFAULT_CHECK_IN = "Check-in date (yyyy-mm-dd)";
    private static final String DEFAULT_CHECK_OUT = "Check-out date (yyyy-mm-dd)";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BookRoomStaffController(BookRoomStaff view) {
        this.view = view;
        bookingManger = BookingManager.getInstance();
        hotelManager = HotelManager.getInstance();
        userManager = UserManager.getInstance();
        roomManager = RoomManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getCheckAvailability().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
        updateHotelChoices();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Check Availability".equals(command)) {
            handleBookRoom();
        } else if ("Return".equals(command)) {
            BookingManagement bookingManagementPage = new BookingManagement();
            bookingManagementPage.setVisible(true);
            updateHotelChoices();
            view.dispose();
        }
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
        String username = view.getUsername().getText();
        User user = userManager.getUserData(username);
        if (user == null) {
            JOptionPane.showMessageDialog(view,
                    "Username does not exist",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (user.getType() == UserType.STAFF) {
            JOptionPane.showMessageDialog(view,
                    "Please only enter guest username",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

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
