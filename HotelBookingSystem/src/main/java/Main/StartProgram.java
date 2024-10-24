/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.HotelManager;
import Model.UserManager;
import View.StartMenu;

/**
 *
 * @author minthihakoko
 */
public class StartProgram {
    public static void main(String[] args) {
        
        StartMenu start = new StartMenu();
        start.setVisible(true);
        
    }
    
    private static void databaseSetUp(){
        
        UserManager userManager = new UserManager();
        HotelManager hotelManager = new HotelManager();
        userManager.createDatabase();
        

    }
    
    
    
    
}
