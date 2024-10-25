/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserManager;
import Model.UserType;
import View.GuestMenu;
import View.Register;
import View.SignIn;
import View.StaffMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author minthihakoko
 */
public class RegisterController implements ActionListener {

    private Register view;
    private UserManager um;
    private static final String DEFAULT_USERNAME = "Username";
    private static final String DEFAULT_PASSWORD = "Password";
    private static final String DEFAULT_NAME = "Name";
    private static final String DEFAULT_PHONE = "Phone";
    private static final String DEFAULT_EMAIL = "Email";

    public RegisterController(Register view) {
        this.view = view;
        this.um = UserManager.getInstance();
        initalise();
    }

    private void initalise() {
        view.getSignUpButton().addActionListener(this);
        view.getLoginOption().addActionListener(this);
        view.getUserType().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Login".equals(command)) {
            SignIn signInPage = new SignIn();
            signInPage.setVisible(true);
            view.dispose();
        } else if ("Sign Up".equals(command)) {

            handleRegister();
        }

    }

    private void handleRegister() {
        String username = view.getRegisterUsername().getText();
        if (um.getUserData(username) != null) {
            JOptionPane.showMessageDialog(view,
                    "Username already exists",
                    "Registeration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String password = view.getRegisterPassword().getText();
        String name = view.getRegisterName().getText();
        String phone = view.getRegisterPhone().getText();
        String email = view.getRegisterEmail().getText();

        if (isDefaultOrEmpty(username, DEFAULT_USERNAME)
                || isDefaultOrEmpty(password, DEFAULT_PASSWORD)
                || isDefaultOrEmpty(name, DEFAULT_NAME)
                || isDefaultOrEmpty(phone, DEFAULT_PHONE)
                || isDefaultOrEmpty(email, DEFAULT_EMAIL)) {

            JOptionPane.showMessageDialog(view,
                    "Please fill in all fields with valid information",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(username, password, name, phone, email);
        String userTypeString = view.getUserType().getSelectedItem().toString();
        UserType userType = null;
        if ("Guest".equals(userTypeString)) {
            userType = UserType.GUEST;
        } else if ("Staff".equals(userTypeString)) {
            userType = UserType.STAFF;

        }
        newUser.setType(userType);
        um.registerUser(newUser);
        if (userType == UserType.GUEST) {
            GuestMenu guestMenu = new GuestMenu();
            guestMenu.setVisible(true);

        } else if (userType == UserType.STAFF) {
            StaffMenu staffMenu = new StaffMenu();
            staffMenu.setVisible(true);
        }
        view.dispose();
        um.closeConnection();
    }

    private boolean isDefaultOrEmpty(String value, String defaultValue) {
        return value == null || value.isEmpty() || value.equals(defaultValue);
    }

}
