/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BookingManager;
import Model.HotelManager;
import Model.RoomManager;
import Model.UserManager;
import View.BookRoomGuest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 *
 * @author alex
 */
public class BookRoomGuestController implements ActionListener {
    
    BookRoomGuest view;
    BookingManager bookingManager;
    UserManager userManager;
    HotelManager hotelManager;
    RoomManager roomManager;
    
    
    private static final String DEFAULT_CHECK_IN = "Check-in date (yyyy-mm-dd)";
    private static final String DEFAULT_CHECK_OUT = "Check-out date (yyyy-mm-dd)";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
