/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AddNewHotel;
import Model.HotelManager;
import View.StaffMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class AddNewHotelController implements ActionListener {

    AddNewHotel view;
    private HotelManager hm;
    private static final String DEFAULT_NAME = "Enter Hotel Name";
    private static final String DEFAULT_ADDRESS = "Enter Hotel Address";

    public AddNewHotelController(AddNewHotel view) {
        this.view = view;
        this.hm = HotelManager.getInstance();
        initalise();
    }

    private void initalise() {
        view.getAddNewHotel().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Add New Hotel".equals(command)) {
            handleAddHotel();
            StaffMenu staffMenu = new StaffMenu();
            staffMenu.setVisible(true);
            view.dispose();
        }
    }

    private void handleAddHotel() {
        String name = view.getHotelName().getText();
        String address = view.getHotelAddress().getText();

        if (isDefaultOrEmpty(name, DEFAULT_NAME) || isDefaultOrEmpty(address, DEFAULT_ADDRESS)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate room counts
        int standardRooms = getValidatedRoomCount(view.getStandardRoomsNumber().getText(), "Standard Rooms");
        if (standardRooms == -1) {
            return;
        }
        int premiumRooms = getValidatedRoomCount(view.getPremiumRoomsNumber().getText(), "Premium Rooms");
        if (premiumRooms == -1) {
            return;
        }
        int suites = getValidatedRoomCount(view.getNumberOfSuites().getText(), "Suites");
        if (suites == -1) {
            return;
        }
        // Create the new hotel
        hm.createNewHotel(name, address, standardRooms, premiumRooms, suites);

        JOptionPane.showMessageDialog(view,
                "Hotel created successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        

    }

    private int getValidatedRoomCount(String text, String roomType) {
        try {
            int count = Integer.parseInt(text);
            if (count < 0) {
                showError(roomType + " cannot be negative.");
                return -1;
            }
            return count;
        } catch (NumberFormatException e) {
            showError("Please enter a valid integer for " + roomType + ".");
            return -1;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
    }

}
