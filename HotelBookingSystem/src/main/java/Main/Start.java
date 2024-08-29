/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import MainHotel.BookingManager;
import java.util.Scanner;
import MainHotel.UserManager;
import MainHotel.User;
import MainHotel.UserType;
import MainHotel.Guest;
import MainHotel.HotelManager;
import MainHotel.RoomManager;
import MainHotel.Staff;

/**
 *
 * @author minthihakoko
 */
public class Start {

    public static void main(String[] args) {
        UserManager userManager = new UserManager("./database/user.csv");
        RoomManager roomManager = new RoomManager("./database/room.csv");
        
        HotelManager hotelManager = new HotelManager("./database/hotel.csv", roomManager);
        BookingManager bookingManager = new BookingManager("./database/booking.csv", roomManager, userManager);
        
        userManager.loadData();
        roomManager.loadData();
        hotelManager.loadData();
        bookingManager.loadData();
        
        

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Hotel Booking System");
        System.out.println("1. Sign In");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        User currentUser = null;
        while (true) {
            System.out.print("Enter your choice: ");
            String input = scan.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        currentUser = signInPage(scan, userManager);
                        break;
                    case 2:
                        currentUser = register(scan, userManager);
                        break;
                    case 3:
                        System.out.println("Thank you for using this program!");
                        userManager.saveData();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            if (currentUser != null) {
                if (currentUser instanceof Guest) {
                    Guest guest = (Guest) currentUser;
                    GuestMenu guestMenu = new GuestMenu(guest,roomManager,bookingManager,hotelManager);
                    guestMenu.showMenu();
                } else if (currentUser instanceof Staff) {
                    Staff staff = (Staff) currentUser;
                    StaffMenu staffMenu = new StaffMenu(staff);
                    staffMenu.showMenu();
                }
            } else {
                System.out.println("No user is signed in. Please sign in or register.");
            }

        }

    }

    private static User signInPage(Scanner scan, UserManager userManager) {
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        if (userManager.getUserData(username) == null) {
            System.out.println("User does not exist. Please register.");
            return register(scan, userManager);
        }
        
        System.out.print("Enter password: ");
        String password = scan.nextLine();

        User user = userManager.signIn(username, password);
        if (user != null) {
            return user;
        } else {
            System.out.println("Invalid username or password. Try again");
            return signInPage(scan, userManager);
        }
    }

    private static User register(Scanner scan, UserManager userManager) {
        System.out.print("Enter username: ");
        String username = scan.nextLine();

        if (userManager.getUserData(username) != null) {
            System.out.println("User already exists! Please sign in.");
            return signInPage(scan, userManager);
        }

        System.out.print("Enter password: ");
        String password = scan.nextLine();
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scan.nextLine();
        System.out.print("Enter your email: ");
        String email = scan.nextLine();
        UserType userType;
        while (true) {
            System.out.print("Enter user type (GUEST/STAFF): ");
            String userTypeInput = scan.nextLine();
            try {
                userType = UserType.valueOf(userTypeInput.toUpperCase());

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid user type. Please enter 'GUEST' or 'STAFF'.");
            }
        }
        userManager.registerUser(username, password, name, phone, email, userType);

        userManager.saveData();
        System.out.println("Registration successful! You are automatically signed in");
        return userManager.signIn(username, password);

    }
}
