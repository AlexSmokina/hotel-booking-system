/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The User class represents a user in the Hotel Booking System. 
 * It stores essential user information such as username, password, 
 * name, phone, email, and user type (e.g., guest, staff).
 */
public class User {

    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private UserType type;

    // Constructor initialises a new User object with the provided details.
    public User(String userName, String password, String name, String phone, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // String representation of the User object
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append("Username: ").append(this.getUserName()).append("\n");
        output.append("Name: ").append(this.getName()).append("\n");
        output.append("Phone: ").append(this.getPhone()).append("\n");
        output.append("Email: ").append(this.getEmail()).append("\n");
        output.append("User Type: ").append(this.getType()).append("\n");

        return output.toString();
    }
}
