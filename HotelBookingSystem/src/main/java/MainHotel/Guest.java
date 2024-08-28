/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Guest extends User implements ID{

    //private final String guestID;

    public Guest(String userName, String password,String name, String phone, String email) {

        super(userName,password, name, phone, email);
        super.setType(UserType.GUEST);
        //this.guestID = idGenerator();
        
    }
    
    

    @Override
    public final String idGenerator(Object context) {
        String output = "GS-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

  

}
