/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.Booking;
import Controller.BookingManager;
import Model.Staff;
import Model.Hotel;
import Controller.HotelManager;
import Model.Room;
import Controller.RoomManager;
import java.util.List;
import java.util.Scanner;
import Model.RoomType;
import Model.User;
import Controller.UserManager;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alexander Smokina & Min Thiha Ko Ko
 *
 * The StaffMenu class provides the user interface for staff members to manage
 * hotels, rooms, and bookings within the hotel booking system. This class
 * includes functions for hotel management, room management, and booking
 * management, integrating with various other classes such as HotelManager,
 * RoomManager, and BookingManager.
 */
public class StaffMenu {

    private Staff staff;
    private static Scanner scanner = new Scanner(System.in);
    private HotelManager hotelManager;
    private BookingManager bookingManager;
    private RoomManager roomManager;
    private UserManager userManager;

    // Constructor to initialize StaffMenu with the necessary managers and staff details
    public StaffMenu(Staff staff, HotelManager hotelManager, BookingManager bookingManager, RoomManager roomManager, UserManager userManager) {
        this.staff = staff;
        this.hotelManager = hotelManager;
        this.bookingManager = bookingManager;
        this.roomManager = roomManager;
        this.userManager = userManager;
    }

    // Displays the main menu for staff and handles user input
    public void showMenu() {
        while (true) {
            System.out.println("Welcome " + this.staff.getName());
            System.out.println("Staff Menu:");
            System.out.println("1. Hotel Management");
            System.out.println("2. Room Management");
            System.out.println("3. Booking Management");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        hotelManagement(scanner);
                        break;
                    case 2:
                        roomManagement(scanner);
                        break;
                    case 3:
                        bookingManagement(scanner);
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        System.exit(0);
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Manages hotel-related operations such as adding, updating, and viewing hotels
    public void hotelManagement(Scanner scan) {
        while (true) {
            System.out.println("Hotel Management Menu:");
            System.out.println("1. Add New Hotel");
            System.out.println("2. Update Hotel Details");
            System.out.println("3. View All Hotels");
            System.out.println("4. Return to Previous Menu");
            System.out.print("Enter your choice: ");

            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);
                switch (choice) {
                    case 1:
                        addHotel(scanner);
                        break;
                    case 2:
                        updateHotelDetails(scanner);
                        break;
                    case 3:
                        viewAllHotels();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number (1-4).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Adds new hotel to the system and saves it to the database
    private void addHotel(Scanner scan) {
        System.out.println("Enter Hotel Name: ");
        String hotelName = scanner.nextLine().trim();
        System.out.println("Enter Hotel Address: ");
        String hotelLocation = scanner.nextLine().trim();
        System.out.println("Enter Number of Standard Rooms: ");
        int stdRooms = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter Number of Premium Rooms: ");
        int prmRooms = Integer.parseInt(scan.nextLine().trim());
        System.out.print("Enter Number of Suites: ");
        int suites = Integer.parseInt(scan.nextLine().trim());

        hotelManager.createNewHotel(hotelName, hotelLocation, stdRooms, prmRooms, suites);
        hotelManager.saveData();
        System.out.println("Hotel added successfully!");
    }

    // Updates details of an existing hotel
    private void updateHotelDetails(Scanner scan) {
        System.out.print("Enter Hotel ID to Update: ");
        String hotelID = scan.nextLine().trim();
        Hotel hotel = hotelManager.getHotelData(hotelID);

        if (hotel != null) {
            System.out.print("Enter new Hotel Name (current: " + hotel.getName() + "): ");
            String newName = scan.nextLine().trim();
            if (!newName.isEmpty()) {
                hotel.setName(newName);
            }

            System.out.print("Enter new Location (current: " + hotel.getLocation() + "): ");
            String newLocation = scan.nextLine().trim();
            if (!newLocation.isEmpty()) {
                hotel.setLocation(newLocation);
            }
            hotelManager.updateHotelData(hotelID, hotel);
            hotelManager.saveData();
            System.out.println("Hotel details updated successfully!");
        } else {
            System.out.println("No hotel found with ID: " + hotelID);
        }
    }

    // Displays list of all hotels in the system
    private void viewAllHotels() {
        System.out.println("Listing all hotels:");
        if (hotelManager.getAllHotels().isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            for (Hotel hotel : hotelManager.getAllHotels().values()) {
                System.out.println(hotelManager.dataToString(hotel));
            }
        }
        System.out.println();
    }

    // Manages room-related operations such as adding, updating, and viewing rooms
    public void roomManagement(Scanner scan) {
        while (true) {
            System.out.println("Room Management Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Update Room Details");
            System.out.println("4. View All Rooms");
            System.out.println("5. Return to Previous Menu");
            System.out.print("Enter your choice: ");

            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        addRoom(scanner);
                        break;
                    case 2:
                        removeRoom(scanner);
                        break;
                    case 3:
                        updateRoomDetails(scanner);
                        break;
                    case 4:
                        viewAllRooms();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Adds new room to the specified hotel
    private void addRoom(Scanner scanner) {

        System.out.print("Enter Hotel ID: ");
        String hotelID = scanner.nextLine().trim();
        System.out.print("Enter Room Type (STANDARD / PREMIUM / SUITE): ");
        String roomType = scanner.nextLine().trim().toUpperCase();

        try {
            roomManager.createRoom(roomType, hotelID);
            roomManager.saveData();
            System.out.println("Room added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type. Please enter STANDARD, PREMIUM, or SUITE.");
        }
    }

    // Removes room from the specified hotel
    private void removeRoom(Scanner scanner) {

        System.out.print("Enter Hotel ID: ");
        String hotelID = scanner.nextLine().trim();
        System.out.print("Enter Room ID to Remove: ");
        String roomID = scanner.nextLine().trim();

        Room room = roomManager.getRoomData(roomID, hotelID);
        if (room != null) {
            roomManager.getAllRoomID().remove(roomID);
            roomManager.saveData();
            System.out.println("Room removed successfully!");
        } else {
            System.out.println("No room found with ID: " + roomID + " in hotel ID: " + hotelID);
        }
    }

    // Updates details of an existing room
    private void updateRoomDetails(Scanner scanner) {
        System.out.print("Enter Hotel ID: ");
        String hotelID = scanner.nextLine().trim();
        System.out.print("Enter Room ID to Update: ");
        String roomID = scanner.nextLine().trim();

        Room room = roomManager.getRoomData(roomID, hotelID);
        if (room != null) {
            System.out.print("Enter new Room Type (current: " + room.getRoomType() + "): ");
            String newType = scanner.nextLine().trim();
            if (!newType.isEmpty()) {
                room.setRoomType(RoomType.valueOf(newType.toUpperCase()));
            }

            System.out.print("Enter new Price (current: " + room.getPrice() + "): ");
            String newPrice = scanner.nextLine().trim();
            if (!newPrice.isEmpty()) {
                room.setPrice(Double.parseDouble(newPrice));
            }

            roomManager.updateRoomData(roomID, room);
            roomManager.saveData();
            System.out.println("Room details updated successfully!");
        } else {
            System.out.println("No room found with ID: " + roomID + " in hotel ID: " + hotelID);
        }
    }

    // Displays list of all rooms in the system
    private void viewAllRooms() {
        System.out.println("Listing all rooms:");
        for (Room room : roomManager.getAllRooms().values()) {
            System.out.println(roomManager.dataToString(room));
        }

    }

    // Manages booking-related operations such as booking, canceling, and extending bookings
    public void bookingManagement(Scanner scan) {
        while (true) {
            System.out.println("Booking Management Menu:");
            System.out.println("1. Book a Room");
            System.out.println("2. Cancel booking");
            System.out.println("3. Extend booking");
            System.out.println("4. View guest booking");
            System.out.println("5. Change room for existing booking");
            System.out.println("6. Return to Previous Menu");
            System.out.print("Enter your choice: ");

            String choiceString = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceString);

                switch (choice) {
                    case 1:
                        bookRoom(scanner);
                        break;
                    case 2:
                        cancelBooking(scanner);
                        break;
                    case 3:
                        extendBooking(scanner);
                        break;
                    case 4:
                        System.out.print("Enter guest username: ");
                        String username = scan.nextLine();
                        User user = userManager.getUserData(username);
                        if (user == null) {
                            System.out.println("User(Guest) does not exist!");
                            return;
                        }
                        showBooking(username);
                        break;
                    case 5:
                        // Add the changeRoom option here
                        changeRoom(scanner);
                        break;
                    case 6:
                        return; // Exit to the previous menu
                    default:
                        System.out.println("Invalid choice. Please enter a number (1-6).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Books a room for a guest
    public void bookRoom(Scanner scan) {
        System.out.print("Choose Hotel: (Auckland Skyline,Queenstown Grand)");
        String hotelName = scan.nextLine();

        Hotel hotel = hotelManager.searchHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found. Please try again.");
            return;
        }

        System.out.print("Enter guest username: ");
        String username = scan.nextLine();
        User user = userManager.getUserData(username);
        if (user == null) {
            System.out.println("User(Guest) does not exist!");
            return;
        }

        System.out.print("Check-in date (yyyy-mm-dd): ");
        String startDate = scan.nextLine();
        System.out.print("Check-out date (yyyy-mm-dd): ");
        String endDate = scan.nextLine();

        List<Room> availableRooms = roomManager.filerByDate(startDate, hotel.getHotelID());

        System.out.println("Available Rooms:");
        for (int i = 0; i < availableRooms.size(); i++) {
            Room room = availableRooms.get(i);
            System.out.println((i + 1) + ". " + room);
        }

        System.out.print("Enter the number of the room you want to book: ");
        int roomChoice = scan.nextInt();
        scan.nextLine();

        if (roomChoice < 1 || roomChoice > availableRooms.size()) {
            System.out.println("Invalid choice. Booking canceled. Try again");
            return;
        }

        Room selectedRoom = availableRooms.get(roomChoice - 1);

        Booking booking = bookingManager.createBooking(startDate, endDate, selectedRoom, user, hotel.getHotelID());
        bookingManager.saveData();
        roomManager.saveData();

        if (booking != null) {
            System.out.println("\nRoom " + selectedRoom.getRoomID() + " booked successfully for " + user.getName() + "\nThis is your booking ID: " + booking.getBookingID() + "\n");
            bookingManager.printInvoice(booking.getBookingID());
        } else {
            System.out.println("\nSomething went wrong! Please try again!");
        }
    }

    // Cancels a booking for a guest
    public void cancelBooking(Scanner scan) {
        System.out.print("Enter guest username: ");
        String username = scan.nextLine();
        if (userManager.getUserData(username) == null) {
            System.out.println("User(Guest) does not exist!");
            return;
        }
        if (showBooking(username)) {
            System.out.print("Enter booking ID that you want to cancel: ");
            String bookingID = scan.nextLine();
            bookingManager.cancelBooking(bookingID);
            bookingManager.saveData();
            roomManager.saveData();
        }

    }

    // Extends a booking for a guest
    public void extendBooking(Scanner scan) {
        System.out.print("Enter guest username: ");
        String username = scan.nextLine();
        if (userManager.getUserData(username) == null) {
            System.out.println("User(Guest) does not exist!");
            return;
        }
        if (showBooking(username)) {
            System.out.print("Enter booking ID that you want to extend: ");
            String bookingID = scan.nextLine();
            System.out.println("New check-out date: ");
            String newEndDate = scan.nextLine();
            bookingManager.extendBooking(bookingID, newEndDate);
            bookingManager.saveData();
            roomManager.saveData();

        }
    }

    // Changes the room for an existing booking
    public void changeRoom(Scanner scan) {
        System.out.print("Enter guest username: ");
        String username = scan.nextLine();
        if (userManager.getUserData(username) == null) {
            System.out.println("User(Guest) does not exist!");
            return;
        }
        if (showBooking(username)) {
            System.out.print("Enter booking ID for the room you want to change: ");
            String bookingID = scan.nextLine();

            Booking booking = bookingManager.getBookingData(bookingID);
            if (booking == null) {
                System.out.println("Invalid booking ID. Please try again.");
                return;
            }
            Date startDate = booking.getStartDate();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = dateFormat.format(startDate);
            String hotelID = booking.getHotelID();

            List<Room> availableRooms = roomManager.filerByDate(startDateString, hotelID);

            System.out.println("Available Rooms:");
            for (int i = 0; i < availableRooms.size(); i++) {
                Room room = availableRooms.get(i);
                System.out.println((i + 1) + ". " + room);
            }

            System.out.print("Enter the number of the room you want to switch to: ");
            int roomChoice = scan.nextInt();
            scan.nextLine();

            if (roomChoice < 1 || roomChoice > availableRooms.size()) {
                System.out.println("Invalid choice. Room change canceled.");
                return;
            }

            Room selectedRoom = availableRooms.get(roomChoice - 1);

            bookingManager.changeRoom(bookingID, selectedRoom);
            bookingManager.saveData();
            roomManager.saveData();
        }
    }

    // Displays booking information for a specific guest
    public boolean showBooking(String username) {
        Booking booking = bookingManager.getBookingByUsername(username);
        if (booking != null) {
            System.out.println(booking);
            return true;
        } else {
            System.out.println("There is no booking under that username.");
            return false;
        }
    }

}
