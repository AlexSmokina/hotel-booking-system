/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

/**
 *
 * @author minthihakoko
 */
public class Test {
    public static void main(String[] args) {
        Guest newGuest = new Guest("Alex", "1323", "afwf");
        System.out.println(newGuest.getId());
        System.out.println(newGuest.getName());
        
        Staff newStaff = new Staff("Mike", "54321", "mike@aut.com");
        System.out.println(newStaff.getName());
        System.out.println(newStaff.getPhone());
        System.out.println(newStaff.getStaffID());
    }
}
