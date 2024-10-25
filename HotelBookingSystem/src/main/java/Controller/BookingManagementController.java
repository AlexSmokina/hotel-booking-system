/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BookingManagement;
import View.BookRoomStaff;
import View.CancelBookingStaff;
import View.ChangeRoomStaff;
import View.DisplayBooking;
import View.StartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author minthihakoko
 */
public class BookingManagementController implements ActionListener {

    private BookingManagement view;

    public BookingManagementController(BookingManagement view) {
        this.view = view;
        initialise();
    }

    private void initialise() {
        view.getBookNewRoomButton().addActionListener(this);
        view.getChangeRoomButton().addActionListener(this);
        view.getCancelBookingButton().addActionListener(this);
        view.getExtendBookingButton().addActionListener(this);
        view.getPreviousMenuButton().addActionListener(this);
        view.getViewGuestBookingButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "Book room":
                showView(new BookRoomStaff());
                break;
            case "Cancel booking":
                showView(new CancelBookingStaff());
                break;
            case "Extend booking":
                showView(new CancelBookingStaff());
            case "Change room":
                showView(new ChangeRoomStaff());
                break;
            case "View guest booking":
                showView(new DisplayBooking());
                break;
            case "Return to Previous Menu":
                showView(new StartMenu());
                break;
            default:
                throw new AssertionError();
        }
        
    }

    private void showView(JFrame newView) {
        newView.setVisible(true);
        view.dispose();
    }

}
