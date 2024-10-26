/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ViewAllRooms;
import Model.RoomManager;
import View.RoomManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class ViewAllRoomsController implements ActionListener{
    ViewAllRooms view;
    RoomManager roomManager;
    
    public ViewAllRoomsController(ViewAllRooms view){
        this.view = view;
        this.roomManager = RoomManager.getInstance();
        initialise();
    }
    
    private void initialise(){
        view.getReturnPreviousMenu().addActionListener(this);
        String roomDetails = roomManager.viewRooms();
        view.getRoomDisplayArea().setText(roomDetails);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Return".equals(command)){
            RoomManagement roomManagementPage = new RoomManagement();
            roomManagementPage.setVisible(true);
            view.dispose();
        }
    }
    
   
    
}