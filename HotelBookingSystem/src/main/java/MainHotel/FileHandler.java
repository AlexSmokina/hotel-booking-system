/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;


/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * FileHandler interface provides abstract methods for loading and saving data 
 * to and from files, which can be implemented by various classes handling 
 * different types of data in the hotel booking system.
 */
public interface FileHandler {
    void loadData(); // Abstract method for loading data from a file
    void saveData(); // Abstract method for saving data to a file
}
