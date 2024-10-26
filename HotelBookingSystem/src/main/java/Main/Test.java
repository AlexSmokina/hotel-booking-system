/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Model.Hotel;
import Model.HotelManager;
import java.util.List;
/**
 *
 * @author minthihakoko
 */
public class Test {
    
    public static void main(String[] args) {
        HotelManager hm = HotelManager.getInstance();
        List<String> hotelList = hm.getHotelNames();
        for (String h :hotelList){
            System.out.println(h);
        }
    }
}
