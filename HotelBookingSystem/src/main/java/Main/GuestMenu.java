/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.Booking;
import Controller.BookingManager;
import Model.Guest;
import Model.Hotel;
import Controller.HotelManager;
import Model.Room;
import Controller.RoomManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alexander Smokina & Min Thiha Ko Ko
 * 
 * GuestMenu class handles the user interface for guests interacting with the hotel booking system.
 * It provides options for booking rooms, canceling bookings, extending bookings, and changing rooms.
 */
public class GuestMenu {

    public Guest guest;
    private static Scanner scanner = new Scanner(System.in);
    private RoomManager roomManager;
    private BookingManager bookingManager;
    private HotelManager hotelManager;

    public GuestMenu(Guest guest, RoomManager roomManager, BookingManager bookingManager, HotelManager hotelManager) {
        this.guest = guest;
        this.roomManager = roomManager;
        this.bookingManager = bookingManager;
        this.hotelManager = hotelManager;
    }

    // Displaying guest menu and handles user input to navigate between different options.
    public void showMenu() {
        while (true) {
            System.out.println("\nWelcome " + this.guest.getName());
            System.out.println("Guest Menu:");
            System.out.println("1. Book a Room");
            System.out.println("2. Cancel booking");
            System.out.println("3. Extend booking");
            System.out.println("4. Change Room");
            System.out.println("5. Logout");
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
                        changeRoom(scanner);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number (1-4).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Handles the process of booking a room. Prompts the guest to choose a hotel and select a room from the available options.
     * Saves the booking details once completed.
     */
    public void bookRoom(Scanner scan) {
        System.out.println("Choose Hotel: (Auckland Skyline,Queenstown Grand)");
        String hotelName = scan.nextLine();

        Hotel hotel = hotelManager.searchHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found. Please try again.");
            return;
        }

        System.out.print("Enter Check-in date (yyyy-mm-dd): ");
        String startDate = scan.nextLine();
        System.out.print("Enter Check-out date (yyyy-mm-dd): ");
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

        Booking booking = bookingManager.createBooking(startDate, endDate, selectedRoom, guest, hotel.getHotelID());
        bookingManager.saveData();
        roomManager.saveData();

        if (booking != null) {
            System.out.println("\nRoom " + selectedRoom.getRoomID() + " booked successfully for " + guest.getName() + "\nThis is your booking ID: " + booking.getBookingID()+"\n");
            bookingManager.printInvoice(booking.getBookingID());
        } else {
            System.out.println("\nSomething went wrong! Please try again!");
        }
    }

    /**
     * Allows the guest to cancel an existing booking by entering the booking ID.
     * The booking and room data are updated accordingly.
     */
    public void cancelBooking(Scanner scan) {
        if (showBooking()) {
            System.out.print("Enter booking ID that you want to cancel: ");
            String bookingID = scan.nextLine();
            bookingManager.cancelBooking(bookingID);
            bookingManager.saveData();
            roomManager.saveData();
        }

    }

    /**
     * Enables the guest to extend an existing booking by providing a new check-out date.
     * Updates the booking and room data after the extension.
     */
    public void extendBooking(Scanner scan) {
        if (showBooking()) {
            System.out.print("Enter booking ID that you want to extend: ");
            String bookingID = scan.nextLine();
            System.out.println("New check-out date: ");
            String newEndDate = scan.nextLine();
            bookingManager.extendBooking(bookingID, newEndDate);
            bookingManager.saveData();
            roomManager.saveData();

        }
    }

    /**
     * Allows the guest to change their room during an existing booking.
     * The guest can select a new room from the available options, and the booking is updated accordingly.
     */
    public void changeRoom(Scanner scan) {
        if (showBooking()) {
            System.out.print("Enter the booking ID for the room you want to change: ");
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

    /**
     * Displays the guest's current booking details.
     * Returns true if a booking is found, false otherwise.
     */
    public boolean showBooking() {
        Booking booking = bookingManager.getBookingByUsername(this.guest.getUserName());
        if (booking != null) {
            System.out.println(booking);
            return true;
        } else {
            System.out.println("There is no booking under your username.");
            return false;
        }
    }

}
