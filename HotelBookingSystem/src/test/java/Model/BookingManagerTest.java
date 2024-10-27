/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexander Smokina
 */
public class BookingManagerTest {

    private BookingManager bookingManager;
    private RoomManager roomManager;
    private UserManager userManager;
    private HotelManager hotelManager;
    private User testUser;
    private String testHotel = "Test Hotel 1";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        // Initialising all required managers
        bookingManager = BookingManager.getInstance();
        roomManager = RoomManager.getInstance();
        userManager = UserManager.getInstance();
        hotelManager = HotelManager.getInstance();

        // Creating fresh tables
        userManager.createDatabase();
        hotelManager.createDatabase();
        roomManager.createDatabase();
        bookingManager.createDatabase();

        // Creating initialise data
        hotelManager.insertInitialData();
        roomManager.insertInitialData();

        // Creating test user
        testUser = new Guest("testUser", "password", "Test Name", "1234567", "test@test.com");
        userManager.registerUser(testUser);
    }

    @AfterEach
    public void tearDown() {
        bookingManager.clearBookingData(testUser.getUserName());

        String hotelID = hotelManager.getHotelIDByName(testHotel);
        roomManager.clearRoomData(hotelID);
        hotelManager.clearHotelData(testHotel);

        if (testUser != null) {
            userManager.deleteUser(testUser.getUserName());
        }

    }

    /**
     * Test creation of a new booking
     */
    @Test
    public void testCreateBooking() {
        // Get a room for booking - add verification
        hotelManager.createNewHotel(testHotel, "Test Location", 1, 1, 1);
        String hotelID = hotelManager.getHotelIDByName(testHotel);
        Room room = roomManager.getRoomData("RM/STD-1", hotelID);
        assertNotNull(room, "Test room should exist");

        // Create booking dates
        String start = "2024-12-01";
        String end = "2024-12-05";

        // Create the booking (using testUser from setUp)
        boolean success = bookingManager.createBooking(start, end, room, testUser, hotelID);

        Booking booking = bookingManager.getBookingByUser(testUser.getUserName());

        // Verify booking was created
        if (success) {
            assertNotNull(booking, "Booking should be created");
            assertEquals("active", booking.getStatus(), "Booking should be Active");
            assertNotNull(booking.getRoom(), "Booking should have a room assigned");
            assertEquals(room.getRoomID(), booking.getRoom().getRoomID(), "Room ID should match");
            assertEquals(testUser.getUserName(), booking.getUserName(), "User should match");
        } else {
            assertNull(booking, "No booking should be created");
        }

    }

    /**
     * Test cancellation of an existing booking
     */
    @Test
    public void testCancelBooking() {
        hotelManager.createNewHotel(testHotel, "Test Location", 1, 1, 1);
        String hotelID = hotelManager.getHotelIDByName(testHotel);
        Room room = roomManager.getRoomData("RM/STD-1", hotelID);
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end = "2024-12-05";

        bookingManager.createBooking(start, end, room, testUser, hotelID);

        String bookingID = bookingManager.getBookingByUser(testUser.getUserName()).getBookingID();
        bookingManager.cancelBooking(bookingID);
        Booking booking = bookingManager.getBookingData(bookingID);

        assertEquals("cancelled", booking.getStatus(), "Booking should be cancelled");
    }

    /**
     * Test extending an existing booking
     */
    @Test
    public void testExtendBooking() {
        hotelManager.createNewHotel(testHotel, "Test Location", 1, 1, 1);
        String hotelID = hotelManager.getHotelIDByName(testHotel);
        Room room = roomManager.getRoomData("RM/STD-1", hotelID);
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end = "2024-12-05";

        bookingManager.createBooking(start, end, room, testUser, hotelID);

        String newEndDate = "2024-12-10";
        String bookingID = bookingManager.getBookingByUser(testUser.getUserName()).getBookingID();
        System.out.println("Extend" + bookingID);

        bookingManager.extendBooking(bookingID, newEndDate);
        Booking booking = bookingManager.getBookingData(bookingID);

        assertEquals("2024-12-10", dateFormat.format(booking.getEndDate()),
                "End date should be extended");
    }

    /**
     * Test invoice generation
     */
    @Test
    public void testDisplayInvoice() {

        hotelManager.createNewHotel(testHotel, "Test Location", 1, 1, 1);
        String hotelID = hotelManager.getHotelIDByName(testHotel);
        Room room = roomManager.getRoomData("RM/STD-1", hotelID);
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end = "2024-12-05";

        bookingManager.createBooking(start, end, room, testUser, hotelID);
        Booking booking = bookingManager.getBookingByUser(testUser.getUserName());

        String invoice = bookingManager.displayInvoice(booking.getBookingID());

        // Printing invoice to console for visual verification
        System.out.println("\nGenerated Invoice:");
        System.out.println(invoice);

        // Verify invoice content
        assertTrue(invoice.contains("BKG"), "Invoice should contain booking ID");
        assertTrue(invoice.contains("testUser"), "Invoice should contain username");
        assertTrue(invoice.contains("Room Rate"), "Invoice should contain rate information");
        assertTrue(invoice.contains("Total Owing"), "Invoice should contain total amount");
    }
}
