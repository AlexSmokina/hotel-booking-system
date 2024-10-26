/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        // Initialising all required managers
        bookingManager = BookingManager.getInstance();
        roomManager = RoomManager.getInstance();
        userManager = UserManager.getInstance();
        hotelManager = HotelManager.getInstance();

        // Clearinganager.clearBookingData();
        hotelManager.clearHotelData();
        roomManager.clearRoomData();

        // Creating fresh tables
        bookingManager.createDatabase();
        hotelManager.createDatabase();

        // Creating test hotel with rooms
        hotelManager.createNewHotel("Test Hotel", "Test Location", 1, 1, 1);

        // Creating test user
        testUser = new Guest("testUser", "password", "Test Name", "1234567", "test@test.com");
        userManager.registerUser(testUser);
    }

    @AfterEach
    public void tearDown() {
        bookingManager.clearBookingData();
        hotelManager.clearHotelData();
        roomManager.clearRoomData();
        
        if(testUser != null) {
            userManager.deleteUser(testUser.getUserName());
        }
        
    }

    /**
     * Test creation of a new booking
     */
    @Test
    public void testCreateBooking() {
        // Get a room for booking - add verification
        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");
        assertNotNull(room, "Test room should exist");

        // Create booking dates
        String start = "2024-12-01";
        String end = "2024-12-05";
  

        // Create the booking (using testUser from setUp)
        bookingManager.createBooking(start, end, room, testUser, "HTL-1");
        

        // Verify booking was created
        Booking booking = bookingManager.getBookingData("BKG-1");
        assertNotNull(booking, "Booking should be created");
        assertEquals("active", booking.getStatus(), "Booking should be active");
        assertNotNull(booking.getRoom(), "Booking should have a room assigned");
        assertEquals(room.getRoomID(), booking.getRoom().getRoomID(), "Room ID should match");
        assertEquals(testUser.getUserName(), booking.getUserName(), "User should match");
    }

    /**
     * Test cancellation of an existing booking
     */
    @Test
    public void testCancelBooking() {
        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end = "2024-12-05";

        bookingManager.createBooking(start, end, room, testUser, "HTL-1");

        bookingManager.cancelBooking("BKG-1");

        Booking booking = bookingManager.getBookingData("BKG-1");
        assertEquals("cancelled", booking.getStatus(), "Booking should be cancelled");
    }

    /**
     * Test extending an existing booking
     */
    @Test
    public void testExtendBooking() {
        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end= "2024-12-05";

        bookingManager.createBooking(start, end, room, testUser, "HTL-1");
        

        String newEndDate = "2024-12-10";
        bookingManager.extendBooking("BKG-1", newEndDate);

        Booking booking = bookingManager.getBookingData("BKG-1");
        assertEquals("2024-12-10", dateFormat.format(booking.getEndDate()),
                "End date should be extended");
    }

    /**
     * Test booking ID generation
     */
    @Test
    public void testIdGenerator() {
        String firstId = bookingManager.idGenerator();
        assertEquals("BKG-1", firstId, "First booking ID should be BKG-1");

        // Create a booking and check next ID
        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");
        String start = "2024-12-01";
        String end = "2024-12-05";
        
        bookingManager.createBooking(start, end, room, testUser, "HTL-1");

        String secondId = bookingManager.idGenerator();
        assertEquals("BKG-2", secondId, "Second booking ID should be BKG-2");
    }

    /**
     * Test invoice generation
     */
    @Test
    public void testDisplayInvoice() {

        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");
        assertNotNull(room, "Test room should exist");

        String start = "2024-12-01";
        String end= "2024-12-05";
        
        bookingManager.createBooking(start, end, room, testUser, "HTL-1");
       
        // Create text area and generate invoice
        JTextArea invoiceArea = new JTextArea();
        bookingManager.displayInvoice("BKG-1", invoiceArea);

        // Printing invoice to console for visual verification
        System.out.println("\nGenerated Invoice:");
        System.out.println(invoiceArea.getText());

        // Verify invoice content
        String invoice = invoiceArea.getText();
        assertTrue(invoice.contains("BKG-1"), "Invoice should contain booking ID");
        assertTrue(invoice.contains("testUser"), "Invoice should contain username");
        assertTrue(invoice.contains("HTL-1"), "Invoice should contain hotel ID");
        assertTrue(invoice.contains("Room Rate"), "Invoice should contain rate information");
        assertTrue(invoice.contains("Total Owing"), "Invoice should contain total amount");
    }
}
