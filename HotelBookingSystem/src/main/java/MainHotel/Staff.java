/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Staff implements ID{
    
    
 
    
    @Override
    public String idGenerator() {
        String output = "STF-";
        int id = (int) (Math.random() * 1001);
        return output + id;
    }
}
