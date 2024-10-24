    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Controller.BookingManager;
import java.util.Scanner;
import Model.UserManager;
import Model.User;
import Model.UserType;
import Model.Guest;
import Controller.HotelManager;
import Controller.RoomManager;
import Model.Staff;

/**
 *
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * This is the entry point for the Hotel Booking System application. 
 * It handles user authentication and provides access to different 
 * functionalities based on the user type (Guest or Staff).
 */
/*public class Start {

    public static void main(String[] args) {
        
        // Initialising managers for users, rooms, hotels, and bookings
        //UserManager userManager = new UserManager("./database/user.csv");
        RoomManager roomManager = new RoomManager("./database/room.csv");
        HotelManager hotelManager = new HotelManager("./database/hotel.csv", roomManager);
        //BookingManager bookingManager = new BookingManager("./database/booking.csv", roomManager, userManager);
        
        // Loading existing data from files
        //userManager.loadData();
        roomManager.loadData();
        hotelManager.loadData();
        //bookingManager.loadData();
        
        // Initialising system with predefined hotels and rooms if none exist
        if(hotelManager.isEmpty()){
            hotelManager.createNewHotel("Auckland Skyline", "Auckland", 2, 2, 1);
            hotelManager.createNewHotel("Queenstown Grand", "Queenstown", 1, 1, 1);
            hotelManager.saveData();
            roomManager.saveData();
        }
        
        
        // Setting up scanner for user input and display initial menu
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Hotel Booking System");
        System.out.println("1. Sign In");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        User currentUser = null;
        // Main loop for handling user input and navigation
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
            // Checking if a user is signed in and show the appropriate menu
            if (currentUser != null) {
                if (currentUser instanceof Guest) {
                    Guest guest = (Guest) currentUser;
                    GuestMenu guestMenu = new GuestMenu(guest,roomManager,bookingManager,hotelManager);
                    guestMenu.showMenu();
                } else if (currentUser instanceof Staff) {
                    Staff staff = (Staff) currentUser;
                    StaffMenu staffMenu = new StaffMenu(staff, hotelManager, bookingManager, roomManager,userManager);
                    staffMenu.showMenu();
                }
            } else {
                System.out.println("No user is signed in. Please sign in or register.");
            }

        }

    }

    // Handling the sign-in process for existing users.
    private static User signInPage(Scanner scan, UserManager userManager) {
        System.out.println("Sign In!");
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

    // Handling the registration process for new users.
    private static User register(Scanner scan, UserManager userManager) {
        System.out.println("Register!");
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
        // Register new user and automatically sign them in
        userManager.registerUser(username, password, name, phone, email, userType);
        userManager.saveData();
        System.out.println("Registration successful! You are automatically signed in");
        return userManager.signIn(username, password);
    }
    
}*/
