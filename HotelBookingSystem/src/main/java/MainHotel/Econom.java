/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
public class Econom extends Room {

    public Econom(String roomType, double price) {
        super("Econom", price);
    }

    @Override
    public String getRoomType() {
        return "Econom";
    }
}
