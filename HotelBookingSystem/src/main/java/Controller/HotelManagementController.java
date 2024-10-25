/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.AddNewHotel;
import View.HotelDetails;
import View.HotelManagement;
import View.StaffMenu;
import View.ViewAllHotels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class HotelManagementController implements ActionListener{
    HotelManagement view;

    public HotelManagementController(HotelManagement view) {
        this.view = view;
        initialise();
    }
    
    private void initialise(){
        view.getAddNewHotelButton().addActionListener(this);
        view.getPreviousMenuButton().addActionListener(this);
        view.getUpdateHotelDetailsButton().addActionListener(this);
        view.getViewAllHotelsButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Add New Hotel".equals(command)){
            AddNewHotel addNewHotelPage = new AddNewHotel();
            addNewHotelPage.setVisible(true);
            view.dispose();
        } else if ("Update Hotel Details".equals(command)){
            HotelDetails hotelDetailsPage = new HotelDetails();
            hotelDetailsPage.setVisible(true);
            view.dispose();
        } else if("View All Hotels".equals(command)){
            ViewAllHotels viewHotelsPage = new ViewAllHotels();
            view.dispose();
        } else {
            StaffMenu staffMenuPage = new StaffMenu();
            staffMenuPage.setVisible(true);
            view.dispose();
        }
    }
    
    
    
}
