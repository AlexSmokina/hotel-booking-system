/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import MainHotel.Staff;
import java.util.Scanner;
import MainHotel.BookingManager;
import MainHotel.HotelManager;
import MainHotel.RoomManager;

/**
 *
 * @author minthihakoko
 */
public class StaffMenu {

    private Staff staff;
    private static Scanner scanner = new Scanner(System.in);
    private HotelManager hotelManager;
    private BookingManager bookingManager;
    private RoomManager roomManager;

    public StaffMenu(Staff staff, HotelManager hotelManager, BookingManager bookingManager, RoomManager roomManager) {
        this.staff = staff;
        this.hotelManager = hotelManager;
        this.bookingManager = bookingManager;
        this.roomManager = roomManager;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Staff Menu:");
            System.out.println("1.");
            System.out.println("2. Room Management");
            System.out.println("3. Booking Management");
            System.out.println("4. View Reports");
            System.out.println("5. Logout");
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
                    case 4:
                        System.exit(0);
                        return;
                    case 5:
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

    public void manageHotels() {

    }

    public void manageRooms() {
        while (true) {
            System.out.println("Room Managemtn Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Update Room Details");
            System.out.println("4. View All Rooms");
            System.out.println("5. Return to Previous Menu");
            System.out.println("Enter your choice: ");
            
            
            
            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        ;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void manageBookings() {

    }
}
