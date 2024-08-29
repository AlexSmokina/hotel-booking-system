/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import MainHotel.Staff;
import java.util.Scanner;

/**
 *
 * @author minthihakoko
 */
public class StaffMenu {

    private Staff staff;
    private static Scanner scanner = new Scanner(System.in);


    public StaffMenu(Staff staff) {
        this.staff = staff;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Staff Menu:");
            System.out.println("1. Hotel Management");
            System.out.println("2. Booking Management");
            System.out.println("3. Logout and Exit");
            System.out.print("Enter your choice: ");

            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        System.exit(0);
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
}
