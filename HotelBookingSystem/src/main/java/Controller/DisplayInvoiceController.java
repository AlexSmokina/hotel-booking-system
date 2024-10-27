/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.UserManager;
import View.DisplayInvoice;
import View.BookingManagement;
import View.GuestMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class DisplayInvoiceController implements ActionListener {

    DisplayInvoice view;
    BookingManager bookingManager;
    UserManager userManager;

    public DisplayInvoiceController(DisplayInvoice view) {
        
        this.view = view;
        this.bookingManager = BookingManager.getInstance();
        this.userManager = UserManager.getInstance();
        initialise();
    }

    private void initialise() {
        
        view.getSearch().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
        view.getInvoiceTextArea().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String userType = userManager.getCurrentUser().getType().toString();
        String command = e.getActionCommand();
        
        if ("Search".equals(command)) {
            String bookingId = view.getEnterBookingID().getText().trim();
            String invoice = bookingManager.displayInvoice(bookingId);
            view.getInvoiceTextArea().setText(invoice);
            
        } else if (userType.equalsIgnoreCase("STAFF") && "Return".equals(command)) {

            BookingManagement bookingManagementPage = new BookingManagement();
            bookingManagementPage.setVisible(true);
            view.dispose();
            
        } else if (userType.equalsIgnoreCase("GUEST") && "Return".equals(command)) {
            
            GuestMenu guestMenuPage = new GuestMenu();
            guestMenuPage.setVisible(true);
            view.dispose();  
        }
    }
}
