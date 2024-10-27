/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.Room;
import Model.RoomManager;
import Model.User;
import Model.UserManager;
import Model.HotelManager;
import Model.UserType;
import View.BookRoomGuest;
import View.BookRoomStaff;
import View.BookingManagement;
import View.GuestMenu;
import View.RoomAvailability;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class RoomAvailabilityController implements ActionListener {

    RoomAvailability view;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private UserManager userManager;
    private HotelManager hotelManager;
    private String username;
    private String checkInDate;
    private String checkOutDate;
    private String hotelName;
    private List<Room> availableRooms;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RoomAvailabilityController(RoomAvailability view, String username, String checkInDate, String checkOutDate, String hotelName) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        this.userManager = UserManager.getInstance();
        this.hotelManager = HotelManager.getInstance();
        this.username = username;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelName = hotelName;
        initialize();
    }

    private void initialize() {
        view.getConfirmBooking().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
        displayAvailableRooms();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        UserType userType = userManager.getCurrentUser().getType();
        String command = e.getActionCommand();

        if ("Confirm Booking".equals(command)) {
            handleBookingConfirmation();
        } else if (userType.equals(UserType.STAFF) && "Return".equals(command)) {
            BookRoomStaff bookRoomStaff = new BookRoomStaff();
            bookRoomStaff.setVisible(true);
            view.dispose();
        } else if (userType.equals(UserType.GUEST) && "Return".equals(command)) {
            BookRoomGuest bookRoomGuest = new BookRoomGuest();
            bookRoomGuest.setVisible(true);
            view.dispose();
        }
    }

    private void handleBookingConfirmation() {
        String roomID = view.getRoomNumber().getText();
        if (roomID == null || roomID.trim().equals("")
                || roomID.equals("?")) {
            JOptionPane.showMessageDialog(view,
                    "Please enter a valid room number",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Room selectedRoom = null;
        for (Room room : availableRooms) {
            if (room.getRoomID().equals(roomID)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(view,
                    "Selected room is not available",
                    "Invalid Room",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userManager.getUserData(username);
        String hotelID = hotelManager.getHotelIDByName(hotelName);
        boolean success = bookingManager.createBooking(checkInDate, checkOutDate, selectedRoom, user, hotelID);

        if (success) {
            JOptionPane.showMessageDialog(view,
                    "Booking confirmed successfully!\n"
                    + "Hotel: " + hotelName + "\n"
                    + "Room ID: " + roomID + "\n"
                    + "Check-in: " + checkInDate + "\n"
                    + "Check-out: " + checkOutDate,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);

            // Returning to the another page depeneding on the user
            exit(new GuestMenu(), new BookingManagement());

        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to create booking. Please try again.",
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void displayAvailableRooms() {
        availableRooms = roomManager.filterByDate(checkInDate, hotelName);

        StringBuilder roomInfo = new StringBuilder();

        // Add header line with labels
        roomInfo.append("Room Type, Room ID, Price, Availability Status, Available From, Hotel ID\n");
        roomInfo.append("===================\n");

        for (Room room : availableRooms) {
            roomInfo.append(room.getRoomType()).append(", ")
                    .append(room.getRoomID()).append(", ")
                    .append("$").append(room.getPrice()).append(", ")
                    .append(room.isBooked() ? "Booked" : "Available").append(", ")
                    .append(dateFormat.format(room.getAvailabilityDate())).append(", ")
                    .append(room.getHotelID()).append("\n");
        }
        view.getRoomOptionArea().setText(roomInfo.toString());
    }

    private void exit(JFrame guestView, JFrame staffView) {
        if (userManager.getCurrentUser().getType().equals(UserType.GUEST)) {
            guestView.setVisible(true);
            view.dispose();
        } else {
            staffView.setVisible(true);
            view.dispose();
        }
    }

}
