/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko
 *
 * The UserManager class is responsible for managing user data within the Hotel
 * Booking System. It handles the loading and saving of user data from/to files,
 * user authentication, and the registration and updating of user information.
 */
public class UserManager implements DatabaseCreator {

    private static UserManager instance = null;
    private final DbManager dbManager;
    private final Connection conn;
    private Statement statement;
    private static User currentUser; // Make currentUser static

    private UserManager() {
        dbManager = DbManager.getInstance();
        conn = dbManager.getConnection();
        currentUser = null;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    @Override
    public void createDatabase() {
        try {
            statement = conn.createStatement();
            if (dbManager.doesTableExist("USER")) {
                statement.executeUpdate("DROP TABLE USER");
            }

            String createUserDB = "CREATE TABLE USERS ("
                    + "USERNAME VARCHAR(20), "
                    + "PASSWORD VARCHAR(30), "
                    + "NAME VARCHAR(30), "
                    + "PHONE VARCHAR(20), "
                    + "EMAIL VARCHAR(40), "
                    + "USER_TYPE VARCHAR(10))";
            dbManager.updateDB(createUserDB);

            System.out.println("USER table created");

        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public User getUserData(String username) {
        User user = null;
        String userQuery = "SELECT * FROM USERS WHERE USERNAME = '" + username + "'";
        try {
            ResultSet resultSet = dbManager.queryDB(userQuery);

            if (resultSet.next()) {
                String password = resultSet.getString("PASSWORD");
                String name = resultSet.getString("NAME");
                String phone = resultSet.getString("PHONE");
                String email = resultSet.getString("EMAIL");
                String userTypeString = resultSet.getString("USER_TYPE");
                UserType userType = UserType.valueOf(userTypeString);

                user = new User(username, password, name, phone, email);
                user.setType(userType);
            }

            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(HotelManager.class.getName()).log(Level.SEVERE, null, ex);

        }
        return user;
    }

    // Authenticates a user by checking if the provided username and password match
    public User signIn(String username, String password) {
        User user = getUserData(username);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user; // Sign-in successful
        } else {
            return null; // Invalid username or password
        }
    }

    public void registerUser(User user) {
        String insertUserSQL = "INSERT INTO USERS VALUES ('"
                + user.getUserName() + "', '"
                + user.getPassword() + "', '"
                + user.getName() + "', '"
                + user.getPhone() + "', '"
                + user.getEmail() + "', '"
                + user.getType().toString() + "')";

        dbManager.updateDB(insertUserSQL);
        System.out.println(user.getUserName() + " registered successfully!");

    }

    // Updates the user data in the usersData map
    public boolean updateUserData(String username, User newUserData) {
        User user = getUserData(username);
        if (user == null || newUserData == null) {
            return false;
        }
        String updateUserSQL = "UPDATE USERS SET "
                + "PASSWORD = '" + newUserData.getPassword() + "', "
                + "NAME = '" + newUserData.getName() + "', "
                + "PHONE = '" + newUserData.getPhone() + "', "
                + "EMAIL = '" + newUserData.getEmail() + "', "
                + "USER_TYPE = '" + newUserData.getType().toString() + "' "
                + "WHERE USERNAME = '" + newUserData.getUserName() + "'";

        dbManager.updateDB(updateUserSQL);

        return true;
    }

    // Return current logged in user
    public User getCurrentUser() {
        return currentUser;
    }

    // Method to delete current user
    public boolean deleteUser(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        String deleteUserSQL = "DELETE FROM USERS WHERE USERNAME = '" + username + "'";
        try {
            dbManager.updateDB(deleteUserSQL);
            return true; // Return true *only* if the update was successful.
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
