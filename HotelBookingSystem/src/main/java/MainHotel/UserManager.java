/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The UserManager class is responsible for managing user data within the Hotel Booking System.
 * It handles the loading and saving of user data from/to files, user authentication, 
 * and the registration and updating of user information.
 */
public class UserManager implements FileHandler {

    private String fileName;
    private Map<String, User> usersData;

    // Constructor to initialise the UserManager with a specified file name
    public UserManager(String fileName) {
        this.fileName = fileName;
        usersData = new HashMap<>();
    }
    
    // Loads user data from a file and populates the usersData map
    @Override
    public void loadData() {
        usersData.clear();
        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            bufferReader.readLine();
            String line;
            while ((line = bufferReader.readLine()) != null) {
                User user = parseUser(line);
                if (user != null) {
                    usersData.put(user.getUserName(), user);
                }

            }
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    // Saves the current state of usersData to a file
    @Override
    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Username,Password,Name,Phone,Email,User Type");
            for (User user : usersData.values()) {
                printWriter.println(dataToString(user));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    // Parses a line of text from the file into a User object
    private User parseUser(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");
        
        String username = parts[0];
        String password = parts[1];
        String name = parts[2];
        String phone = parts[3];
        String email = parts[4];
        UserType userType;

        try {
            userType = UserType.valueOf(parts[5]);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type in data: " + parts[5]);
            return null;
        }
        
        User user = (userType == UserType.GUEST)
                ? new Guest(username, password, name, phone, email) 
                : new Staff(username, password, name, phone, email);
        return user;
    }

    // Converts a User object into a string for saving to the file
    private String dataToString(User user) {
        String output;
        output = String.format("%s,%s,%s,%s,%s,%s", user.getUserName(), user.getPassword(),
                user.getName(), user.getPhone(), user.getEmail(), user.getType());
        return output;
    }

    // Registers a new user and adds them to the usersData map
    public void registerUser(String username, String password, String name, String phone, String email, UserType type) {
        User newUser = (type == UserType.GUEST)
                ? new Guest(username, password, name, phone, email)
                : new Staff(username, password, name, phone, email);
        usersData.put(username, newUser);
    }

    // Authenticates a user by checking if the provided username and password match
    public User signIn(String username, String password) {
        User user = usersData.get(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUserData(String username) {
        return usersData.get(username);
    }

    // Updates the user data in the usersData map
    public boolean updateUserData(String username, User newUserData) {
        if (!usersData.containsKey(username)) {
            return false;
        } else {
            usersData.put(username, newUserData);
            return true;
        }
    }
    
    // Retrieves all usernames from the usersData map
    public Set<String> getAllUsernames() {
        return usersData.keySet();
    }

}
