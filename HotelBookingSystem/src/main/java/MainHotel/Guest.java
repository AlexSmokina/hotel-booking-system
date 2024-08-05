/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Guest implements ID extends User {

    private String id;

    public Guest(String name, String phone, String email) {

        super(name, phone, email);
        this.id = idGenerator();
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
        return id;
    }

}