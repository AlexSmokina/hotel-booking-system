package Controller;

import Model.HotelManager;
import View.HotelDetails;
import View.HotelManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author minthihakoko
 */
public class HotelDetailController implements ActionListener {

    HotelDetails view;
    private HotelManager hm;
    private static final String DEFAULT_ID = "Enter hotel ID to update";
    private static final String DEFAULT_NAME = "Enter new hotel name";
    private static final String DEFAULT_ADDRESS = "Enter new location";

    public HotelDetailController(HotelDetails view) {
        this.view = view;
        this.hm = HotelManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getConfirm().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Confirm".equals(command)) {
            handleChangeDetails();
        }
    }

    private void handleChangeDetails() {
        String hotelID = view.getHotelID().getText();
        if (hm.getHotelData(hotelID) == null) {
            JOptionPane.showMessageDialog(view,
                    "Wrong Hotel ID",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = view.getNewHotelName().getText();
        String address = view.getHotelLocation().getText();

        if (isDefaultOrEmpty(name, DEFAULT_NAME) || isDefaultOrEmpty(address, DEFAULT_ADDRESS)) {
            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        hm.updateHotelDetails(hotelID, name, address);
        JOptionPane.showMessageDialog(view,
                "Detail edited successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        HotelManagement hotelManagementMenu = new HotelManagement();
        hotelManagementMenu.setVisible(true);
        view.dispose();
    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
    }

}
