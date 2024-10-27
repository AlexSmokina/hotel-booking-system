/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Booking;
import Model.BookingManager;
import Model.Hotel;
import Model.Room;
import Model.RoomManager;
import Model.HotelManager;
import View.BookRoomStaff;
import View.BookingManagement;
import View.ChangeRoomAvailability;
import View.ChangeRoomStaff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class ChangeRoomAvailabilityController implements ActionListener {

    ChangeRoomAvailability view;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private String bookingID;
    private List<Room> availableRooms;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ChangeRoomAvailabilityController(ChangeRoomAvailability view, String bookingID ) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        this.hotelManager = HotelManager.getInstance();
        this.bookingID = bookingID;
    initialize();
}

    private void initialize() {
        view.getChangeBooking().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
        displayAvailableRooms();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Change Room".equals(command)) {
            handleChangeRoom();
        } else if ("Return".equals(command)) {
            ChangeRoomStaff changeRoomStaff = new ChangeRoomStaff();
            changeRoomStaff.setVisible(true);
            view.dispose();
        }
    }

    private void handleChangeRoom() {
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
        
       boolean success = bookingManager.changeRoom(bookingID, selectedRoom);

       if (success) {
            JOptionPane.showMessageDialog(view,
                    "Room changed successfully!\n"
                    + "New Room ID: " + roomID + "\n"
                    + "Room Type: " + selectedRoom.getRoomType() + "\n",
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);

            // Return to booking management page
            BookingManagement bookingManagement = new BookingManagement();
            bookingManagement.setVisible(true);
            view.dispose();

        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to create booking. Please try again.",
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void displayAvailableRooms() {
        
        String today = dateFormat.format(Room.getTodayDate());  // Use new Date() directly
        Booking booking =bookingManager.getBookingData(bookingID);
        String hotelID = booking.getHotelID();
        Hotel hotel = hotelManager.getHotelData(hotelID);
        String hotelName = hotel.getName();
        
        availableRooms = roomManager.filterByDate(today, hotelName);

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


}
