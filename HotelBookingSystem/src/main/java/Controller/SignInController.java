/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserManager;
import Model.UserType;
import View.Register;
import View.SignIn;
import View.GuestMenu;
import View.StaffMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class SignInController implements ActionListener {

    private SignIn view;
    private UserManager userManager;
    

    public SignInController(SignIn view) {
        this.view = view;
        this.userManager = UserManager.getInstance();
        initalise();
    }

    private void initalise() {
        view.getLogInButton().addActionListener(this);
        view.getSignUpOptionButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Sign Up".equals(command)) {
            Register registerPage = new Register();
            registerPage.setVisible(true);
            view.dispose();
        } else if ("Login".equals(command)) {

            handleSignIn();
        }

    }

    public void handleSignIn() {
        String username = view.getLoginUsername().getText();
        String password = view.getLoginPassward().getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Username and password cannot be empty.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = userManager.signIn(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(view,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view,
                username+" signed in successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

            if (user.getType().equals(UserType.GUEST)) {
                GuestMenu guestMenu = new GuestMenu();
                guestMenu.setVisible(true);

            } else  {
                StaffMenu staffMenu = new StaffMenu();
                staffMenu.setVisible(true);
            }
            view.dispose();
        }

    }

}
