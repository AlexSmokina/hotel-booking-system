/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.CancelBookingStaff;
import Model.BookingManager;
import View.BookingManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class CancelBookingStaffController implements ActionListener{
    CancelBookingStaff view;
    BookingManager bookingManager;
    public CancelBookingStaffController(CancelBookingStaff view) {
        this.view = view;
        this.bookingManager = BookingManager.getInstance();
        initialise();
    }
    
    private void initialise(){
        view.getConfirmCancel().addActionListener(this);
        view.getReturnPreviousMenu().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Confirm".equals(command)){
            handleCancelBooking();
        } else if("Return".equals(command)){
            BookingManagement bookingManagementPage = new BookingManagement();
            bookingManagementPage.setVisible(true);
            view.dispose();
        }
        
    }

    private void handleCancelBooking() {
        
    }
    
   
    
    
    
}
