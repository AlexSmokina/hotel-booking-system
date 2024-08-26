/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Premium extends Room {
    
    public Premium(String roomType, double price) {
        super("Premium", price);
    }
    
        @Override
    public String getRoomType() {
        return "Premium";
    }
}
