/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.UserManager;
import View.BookingManagement;
import View.ViewBooking;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class ViewBookingController implements ActionListener {

    ViewBooking view;
    UserManager userManager;
    BookingManager bookingManager;

    public ViewBookingController(ViewBooking view) {
        this.view = view;
        this.userManager = UserManager.getInstance();
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }

    private void initialise() {
        view.getSearch().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Search".equals(command)) {
            handleBookingSearch();

        } else if ("Return".equals(command)) {
            BookingManagement bookingManagementPage = new BookingManagement();
            bookingManagementPage.setVisible(true);
            view.dispose();
        }
    }

    private void handleBookingSearch() {
        String username = view.getEnterGuestName().getText();
        if (userManager.getUserData(username) == null) {
            JOptionPane.showMessageDialog(view,
                    "Username does not exist",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bookingDetail = bookingManager.viewBookingsByUser(username);
        view.getBookingViewArea().setText(bookingDetail);

    }

}
