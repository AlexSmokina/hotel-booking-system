/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HotelManager;
import Model.Room;
import View.ViewAllRooms;
import Model.RoomManager;
import View.RoomManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author minthihakoko
 */
public class ViewAllRoomsController implements ActionListener {

    ViewAllRooms view;
    RoomManager roomManager;
    HotelManager hotelManager;

    public ViewAllRoomsController(ViewAllRooms view) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        this.hotelManager = HotelManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getReturnPreviousMenu().addActionListener(this);
        view.getSearch().addActionListener(this);
        updateHotelChoices();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Search".equals(command)) {
            handleSearch();
        } else if ("Return".equals(command)) {
            RoomManagement roomManagementPage = new RoomManagement();
            roomManagementPage.setVisible(true);
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

    // Method to view rooms based on hotel choice
    private void handleSearch() {
        String hotelName = view.getHotelChoice().getSelectedItem().toString();
        List<Room> rooms = roomManager.filterRoomByHotel(hotelName);

        StringBuilder roomInfo = new StringBuilder();
        int counter = 1;

        for (Room room : rooms) {
            roomInfo.append("==========================\n");
            roomInfo.append(String.format("                 ROOM # %d              \n", counter));
            roomInfo.append("==========================\n");
            roomInfo.append(String.format("Room ID           : %s\n", room.getRoomID()));
            roomInfo.append(String.format("Room Type         : %s\n", room.getRoomType()));
            roomInfo.append(String.format("Price             : $%.2f\n", room.getPrice()));
            roomInfo.append(String.format("Availability      : %s\n", (room.isBooked()) ? "Booked" : "Available"));
            roomInfo.append(String.format("Date Available    : %s\n", room.getAvailabilityDate()));
            roomInfo.append(String.format("Hotel ID          : %s\n", room.getHotelID()));
            roomInfo.append("----------------------------------------\n");

            counter++;
        }
        view.getRoomDisplayArea().setText(roomInfo.toString());
    }

}
