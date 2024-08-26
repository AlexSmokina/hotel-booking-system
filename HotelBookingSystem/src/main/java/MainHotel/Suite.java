/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Suite extends Room {
    
    public Suite(String roomType, double price) {
        super("Suite", price);
    }
    
    @Override
    public String getRoomType() {
        return "Suite";
    }
    
}
