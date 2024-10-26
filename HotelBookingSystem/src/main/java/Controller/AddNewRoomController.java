/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.RoomManager;
import View.AddNewRoom;
import View.RoomManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class AddNewRoomController implements ActionListener {

    AddNewRoom view;
    RoomManager roomManager;

    private static final String DEFAULT_ID = "Enter Hotel ID";

    public AddNewRoomController(AddNewRoom view) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        initalise();

    }

    private void initalise() {
        view.getAddNewRoom().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Add New Room".equals(command)) {
            handleAddRoom();
        } else if ("Return".equals(command)) {
            RoomManagement roomManagementMenu = new RoomManagement();
            roomManagementMenu.setVisible(true);
            view.dispose();
        }

    }

    private void handleAddRoom() {
        String hotelID = view.getHotelID().getText();
        String roomType = view.getRoomType().getSelectedItem().toString();

        if (isDefaultOrEmpty(hotelID, DEFAULT_ID)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the new room
        roomManager.createRoom(roomType, hotelID);

        JOptionPane.showMessageDialog(view,
                "Room created successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        RoomManagement roomManagementMenu = new RoomManagement();
        roomManagementMenu.setVisible(true);
        view.dispose();

    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
    }
}
