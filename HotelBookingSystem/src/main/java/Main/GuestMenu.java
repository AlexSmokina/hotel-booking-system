/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import MainHotel.Guest;
import java.util.Scanner;

/**
 *
 * @author minthihakoko
 */
public class GuestMenu {

    public Guest guest;
    private static Scanner scanner = new Scanner(System.in);

    public GuestMenu(Guest guest) {
        this.guest = guest;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Welcome "+this.guest.getName());
            System.out.println("Guest Menu:");
            System.out.println("1. Book a Room");
            System.out.println("2. Cancel booking");
            System.out.println("3. Extend booking");
            System.out.println("4. Log out and exit");
            System.out.print("Enter your choice: ");
            
            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        
                        break;
                    case 2:
                        System.exit(0);
                        break;
                    case 3:
                        System.exit(0);
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    public void bookRoom(Scanner scan){
        System.out.println("Choose Hotel: (A,B)");
        String hotelName = scan.nextLine();
        System.out.println("Check-in date ");
        String startDate = scan.nextLine();
        System.out.println("Check-out date: ");
        String endDate = scan.nextLine();
        
        this.guest.createBooking(hotelName,startDate,endDate);
    }

}
