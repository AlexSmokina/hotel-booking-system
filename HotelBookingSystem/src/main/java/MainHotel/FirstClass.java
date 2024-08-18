/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class FirstClass extends Room {
    
    public FirstClass(String roomType, double price) {
        super("First Class", price);
    }
    
        @Override
    public String getRoomType() {
        return "First Class";
    }
}
