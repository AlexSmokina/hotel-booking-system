/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The Guest class represents a guest user in the hotel booking system. 
 * It extends the User class and sets the user type to GUEST.
 */
public class Guest extends User{

    // Constructor for the Guest class.
    public Guest(String userName, String password,String name, String phone, String email) {

        super(userName,password, name, phone, email);
        super.setType(UserType.GUEST);  
    }
}
