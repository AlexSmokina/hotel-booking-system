/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Suit extends Room {
    
    public Suit(String roomType, double price) {
        super("Suit", price);
    }
    
    @Override
    public String getRoomType() {
        return "Suit";
    }
    
}
