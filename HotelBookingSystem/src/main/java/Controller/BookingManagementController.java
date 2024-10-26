/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BookingManagement;
import View.BookRoomStaff;
import View.CancelBookingStaff;
import View.ChangeRoomStaff;
import View.DisplayInvoice;
import View.ExtendBookingStaff;
import View.ViewBooking;
import View.StaffMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author minthihakoko
 */
public class BookingManagementController implements ActionListener {

    private final BookingManagement view;

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
        view.getDisplayInvoice().addActionListener(this);

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
                showView(new ExtendBookingStaff());
                break;
            case "Change room":
                showView(new ChangeRoomStaff());
                break;
            case "View guest booking":
                showView(new ViewBooking());
                break;
            case "Display Invoice":
                showView(new DisplayInvoice());
                break;
            case "Return to Previous Menu":
                showView(new StaffMenu());
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
