/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Room;
import View.RoomDetails;
import View.RoomManagement;
import Model.RoomManager;
import Model.RoomType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class RoomDetailController implements ActionListener {

    RoomDetails view;
    RoomManager roomManager;
    
    private static final String DEFAULT_HOTELID = "Enter hotel ID";
    private static final String DEFAULT_ROOMID = "Enter Room Type (STANDARD / PREMIUM / SUITE)";
    private static final String DEFAULT_PRICE = "Enter new price";

    public RoomDetailController(RoomDetails view) {
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getConfirm().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Confirm Update".equals(command)) {
            handleEditDetail();
        } else if ("Return".equals(command)) {
            RoomManagement roomManagementMenu = new RoomManagement();
            roomManagementMenu.setVisible(true);
            view.dispose();
        }
    }

    private void handleEditDetail() {
        String hotelID = view.getHotelID().getText();
        String roomID = view.getRoomID().getText();
        String price = view.getRoomPrice().getText();
        String roomTypeString  = view.getRoomType().getSelectedItem().toString();

        if (isDefaultOrEmpty(hotelID, DEFAULT_HOTELID) || isDefaultOrEmpty(roomID, DEFAULT_ROOMID) || isDefaultOrEmpty(price, DEFAULT_PRICE)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        Room room = roomManager.getRoomData(roomID, hotelID);
        RoomType roomType = RoomType.valueOf(roomTypeString.toUpperCase());
        room.setRoomType(roomType);
        room.setPrice(Double.parseDouble(price));
        roomManager.updateRoomData(roomID, room);
        
        JOptionPane.showMessageDialog(view,
                "Detail edited successfully!",
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
