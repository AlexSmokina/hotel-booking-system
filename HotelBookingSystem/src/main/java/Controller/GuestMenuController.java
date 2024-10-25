/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BookRoomGuest;
import View.CancelBookingGuest;
import View.ChangeRoomGuest;
import View.ExtendBookingGuest;
import View.GuestMenu;
import View.StartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author minthihakoko
 */
public class GuestMenuController implements ActionListener{
    GuestMenu view;
    public GuestMenuController(GuestMenu view) {
        this.view = view;
        initialise();
        
    }
    
    private void initialise(){
        view.getBookRoomButton().addActionListener(this);
        view.getCancelBookingButton().addActionListener(this);
        view.getExtendBookingButton().addActionListener(this);
        view.getChangeRoomButton().addActionListener(this);
        view.getLogoutGuestMenuButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Book a room":
                showView(new BookRoomGuest());
                break;
            case "Cancel booking":
                showView(new CancelBookingGuest());
                break;
            case "Extend Booking":
                showView(new ExtendBookingGuest());
                break;
            case "Change Room":
                showView(new ChangeRoomGuest());
                break;
             case "Logout":
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
