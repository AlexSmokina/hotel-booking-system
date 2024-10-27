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
import Model.UserManager;
import Model.UserType;
import View.BookingManagement;
import View.ChangeRoomAvailability;
import View.ChangeRoomGuest;
import View.ChangeRoomStaff;
import View.GuestMenu;
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
public class ChangeRoomAvailabilityController implements ActionListener {

    ChangeRoomAvailability view;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private UserManager userManager;
    private String bookingID;
    private List<Room> availableRooms;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ChangeRoomAvailabilityController(ChangeRoomAvailability view, String bookingID) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        this.hotelManager = HotelManager.getInstance();
        this.userManager = UserManager.getInstance();
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
            exit(new ChangeRoomGuest(), new ChangeRoomStaff());
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

            exit(new GuestMenu(), new BookingManagement());

        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to create booking. Please try again.",
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void displayAvailableRooms() {

        String today = dateFormat.format(Room.getTodayDate());  // Use new Date() directly
        Booking booking = bookingManager.getBookingData(bookingID);
        String hotelID = booking.getHotelID();
        Hotel hotel = hotelManager.getHotelData(hotelID);
        String hotelName = hotel.getName();

        availableRooms = roomManager.filterByDate(today, hotelName);

        StringBuilder roomInfo = new StringBuilder();

        for (Room room : availableRooms) {
            roomInfo.append(String.format("Room ID           : %s\n", room.getRoomID()));
            roomInfo.append(String.format("Room Type         : %s\n", room.getRoomType()));
            roomInfo.append(String.format("Price             : $%.2f\n", room.getPrice()));
            roomInfo.append(String.format("Availability      : %s\n", (room.isBooked()) ? "Booked" : "Available"));
            roomInfo.append(String.format("Date Available    : %s\n", room.getAvailabilityDate()));
            roomInfo.append(String.format("Hotel ID          : %s\n", hotelID));
            roomInfo.append("----------------------------------------\n");
        }
        view.getRoomOptionArea().setText(roomInfo.toString());
    }
    
    private void exit(JFrame guestView, JFrame staffView){
        if(userManager.getCurrentUser().getType().equals(UserType.GUEST)){
            guestView.setVisible(true);
            view.dispose();
        }
        else{
            staffView.setVisible(true);
            view.dispose();
        }
    }

}
