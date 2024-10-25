/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AddNewRoom;
import View.RemoveRoom;
import View.RoomDetails;
import View.RoomManagement;
import View.StaffMenu;
import View.ViewAllRooms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author minthihakoko
 */
public class RoomManagementController implements ActionListener{
    
    private RoomManagement view;
    
    public RoomManagementController(RoomManagement view){
        this.view = view;
        initialise();
    }
    
    private void initialise(){
        view.getPreviousMenuButton().addActionListener(this);
        view.getAddRoomButton().addActionListener(this);
        view.getRemoveRoomButton().addActionListener(this);
        view.getViewAllRoomsButton().addActionListener(this);
        view.getViewAllRoomsButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("Return to Previous Menu".equals(command)){
            StaffMenu staffMenuPage = new StaffMenu();
            staffMenuPage.setVisible(true);
            view.dispose();
    
        } else if ("Add Room".equals(command)){
            AddNewRoom addNewRoomPage = new AddNewRoom();
            addNewRoomPage.setVisible(true);
            view.dispose();
            
        } else if ("Remove Room".equals(command)){
            RemoveRoom removeRoomPage = new RemoveRoom();
            removeRoomPage.setVisible(true);
            view.dispose();
        } else if ("Update Room Details".equals(command)){
            RoomDetails roomDetailsPage = new RoomDetails();
            roomDetailsPage.setVisible(true);
            view.dispose();
        
        } else if("View All Rooms".equals(command)){
            ViewAllRooms viewAllRoomsPage = new ViewAllRooms();
            viewAllRoomsPage.setVisible(true);
            view.dispose();
            
        }  
    }
}
