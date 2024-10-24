/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.StartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.SignIn;
import View.Register;


/**
 *
 * @author minthihakoko
 */

public class StartMenuController implements ActionListener{
    
    private StartMenu view;
    
    
    public StartMenuController(StartMenu view){
        this.view = view;
        initialise();
        
    }
    
    private void initialise(){
        this.view.getSignInButton().addActionListener(this);
        this.view.getRegisterButton().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        e.getActionCommand();
        String command = e.getActionCommand();
        
        
        if ("Sign In".equals(command)) {
            SignIn signInPage = new SignIn();
            signInPage.setVisible(true);
            view.dispose();
        }
        else if("Register".equals(command)){
            Register registerPage = new Register();
            registerPage.setVisible(true);
            view.dispose();
        }
        
    }
    
}
