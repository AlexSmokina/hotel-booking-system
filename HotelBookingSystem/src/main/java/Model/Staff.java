/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko
 * 
 * The Staff class represents a staff member in the hotel booking system. 
 * It extends the User class and sets the user type to STAFF.
 */
public class Staff extends User {

    // Constructor for the Staff class.
    public Staff(String userName, String password, String name, String phone, String email) {
        super(userName, password, name, phone, email);
        super.setType(UserType.STAFF);
    }

}
