/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ViewAllHotels;
import Model.HotelManager;
import View.HotelManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class ViewAllHotelsController implements ActionListener{
    ViewAllHotels view;
    HotelManager hm;
    
    public ViewAllHotelsController(ViewAllHotels view){
        this.view = view;
        hm = HotelManager.getInstance();
        initialise();
    }
    
    private void initialise(){
        view.getReturnPreviousMenu().addActionListener(this);
        String hotelDetails = hm.viewHotels();
        view.getHotelDisplayArea().setText(hotelDetails);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Return".equals(command)){
            HotelManagement hotelManagementPage = new HotelManagement();
            hotelManagementPage.setVisible(true);
            view.dispose();
        }
    }
    
   
    
}
