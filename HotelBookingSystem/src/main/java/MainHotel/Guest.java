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

    private String guestID;

    public Guest(String name, String phone, String email) {

        super(name, phone, email);
        this.guestID = idGenerator();
    }
    
    

    @Override
    public String idGenerator() {
        String output = "GS-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return guestID;
    }

}
