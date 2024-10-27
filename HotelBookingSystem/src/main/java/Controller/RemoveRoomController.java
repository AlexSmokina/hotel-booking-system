/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.RemoveRoom;
import Model.RoomManager;
import View.RoomManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class RemoveRoomController implements ActionListener {

    RemoveRoom view;
    RoomManager roomManager;
    private static final String DEFAULT_HOTELID = "Enter Hotel ID";
    private static final String DEFAULT_ROOMID = "Enter Room ID to Remove";

    public RemoveRoomController(RemoveRoom view) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getConfirmRemove().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Confirm".equals(command)) {
            handleRemoveRoom();
        } else if ("Return".equals(command)) {
            RoomManagement roomManagementMenu = new RoomManagement();
            roomManagementMenu.setVisible(true);
            view.dispose();
        }
    }

    private void handleRemoveRoom() {
        String hotelID = view.getHotelID().getText();
        String roomID = view.getRoomID().getText();

        if (isDefaultOrEmpty(hotelID, DEFAULT_HOTELID) || isDefaultOrEmpty(roomID, DEFAULT_ROOMID)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Room room
        boolean success = roomManager.removeRoom(roomID, hotelID);

        if (success) {
            JOptionPane.showMessageDialog(view,
                    "Room deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            RoomManagement roomManagementPage = new RoomManagement();
            roomManagementPage.setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view,
                    "Failed to delete room. Room may not exist or an error occurred.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
    }
}
