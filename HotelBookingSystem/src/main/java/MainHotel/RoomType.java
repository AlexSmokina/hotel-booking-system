/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package MainHotel;

/**
 *
 * @author alex
 */
// Are we going to uitlise enum RoomType or keep current Room Type Classes
public enum RoomType {
    STANDARD(100.0),
    PREMIUM(150.0),
    SUITE (200.0);
    
    private double price;
    
    RoomType(double price) {  
        this.price = price;
    }
    
    public double getPrice(){
        return this.price;
    }
}
