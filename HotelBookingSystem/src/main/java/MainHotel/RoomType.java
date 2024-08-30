/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package MainHotel;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The RoomType enum represents the different types of rooms available in the hotel,
 * each with a specific price associated with it.
 */
public enum RoomType {
    STANDARD(100.0),
    PREMIUM(150.0),
    SUITE (200.0);
    
    private double price;
    
    // Constructor to initialise the price for each room type
    RoomType(double price) {  
        this.price = price;
    }
    
    // Method to get the price of the room type
    public double getPrice(){
        return this.price;
    }
}
