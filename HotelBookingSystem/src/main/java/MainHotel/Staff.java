/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author Alexander Smokina & Min Thiha Ko Ko 
 */
public class Staff extends User{
    
   
    
    public Staff(String userName, String password, String name, String phone, String email) {
        super(userName, password, name, phone, email);
        super.setType(UserType.STAFF);
    }
 
   


}
