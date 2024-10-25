/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import java.sql.Date;
import javax.swing.JTextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */
public class BookingManagerTest {
    
    private BookingManager bookingManager;
    private static final String TEST_HOTEL_ID = "HTL-001";
    private static final String TEST_ROOM_ID = "RM-101";
    private static final String TEST_USERNAME = "testuser";
    
    @BeforeEach
    public void setUp() {
        // Initialize BookingManager before each test
        bookingManager = new BookingManager();
        bookingManager.createDatabase(); // Reset database to known state
    }

    /**
     * Test creating a new booking with valid data
     * Tests if booking is created and retrieved correctly with STANDARD room
     */
    @Test
    public void testCreateAndGetBooking() {
        // Setup test data
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room room = new Room(TEST_ROOM_ID, RoomType.STANDARD, TEST_HOTEL_ID); // $100 per night
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        // Create booking
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, room, user, TEST_HOTEL_ID, "active");
        
        // Retrieve booking (we'll need to get the generated ID from the database first)
        String generatedId = "BKG-1"; // First booking should have this ID
        Booking retrievedBooking = bookingManager.getBookingData(generatedId);
        
        // Assert booking was created correctly
        assertNotNull(retrievedBooking, "Booking should not be null");
        assertEquals(TEST_USERNAME, retrievedBooking.getUserName(), "Username should match");
        assertEquals(TEST_HOTEL_ID, retrievedBooking.getHotelID(), "Hotel ID should match");
        assertEquals("active", retrievedBooking.getStatus(), "Status should be active");
        assertEquals(100.0, retrievedBooking.getRoom().getPrice(), "Room price should be $100 for STANDARD");
    }

    /**
     * Test creating and retrieving booking with PREMIUM room
     * Verifies correct price and type assignment
     */
    @Test
    public void testCreateBookingPremiumRoom() {
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room room = new Room(TEST_ROOM_ID, RoomType.PREMIUM, TEST_HOTEL_ID); // $150 per night
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, room, user, TEST_HOTEL_ID, "active");
        
        Booking retrievedBooking = bookingManager.getBookingData("BKG-1");
        assertEquals(150.0, retrievedBooking.getRoom().getPrice(), "Room price should be $150 for PREMIUM");
        assertEquals(4 * 150.0, retrievedBooking.getTotalPrice(), "Total price should be calculated for 4 nights");
    }

    /**
     * Test booking ID generator
     * Verifies that generated IDs follow the expected format and sequence
     */
    @Test
    public void testIdGenerator() {
        String bookingId1 = bookingManager.idGenerator();
        String bookingId2 = bookingManager.idGenerator();
        
        // Test ID format
        assertTrue(bookingId1.startsWith("BKG-"), "Booking ID should start with BKG-");
        assertTrue(bookingId2.startsWith("BKG-"), "Booking ID should start with BKG-");
        
        // Test ID sequence
        int id1 = Integer.parseInt(bookingId1.substring(4));
        int id2 = Integer.parseInt(bookingId2.substring(4));
        assertEquals(1, id2 - id1, "Sequential IDs should increment by 1");
    }

    /**
     * Test booking cancellation
     * Verifies that a booking's status is updated correctly when cancelled
     */
    @Test
    public void testCancelBooking() {
        // First create a booking with PREMIUM room
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room room = new Room(TEST_ROOM_ID, RoomType.PREMIUM, TEST_HOTEL_ID);
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, room, user, TEST_HOTEL_ID, "active");
        String bookingId = "BKG-1";
        
        // Cancel the booking
        bookingManager.cancelBooking(bookingId);
        
        // Verify booking status
        Booking cancelledBooking = bookingManager.getBookingData(bookingId);
        assertEquals("cancelled", cancelledBooking.getStatus(), "Booking status should be cancelled");
    }

    /**
     * Test booking extension
     * Verifies that a booking's end date and total price are updated correctly when extended
     */
    @Test
    public void testExtendBooking() {
        // First create a booking with SUITE room
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room room = new Room(TEST_ROOM_ID, RoomType.SUITE, TEST_HOTEL_ID); // $200 per night
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, room, user, TEST_HOTEL_ID, "active");
        String bookingId = "BKG-1";
        
        // Extend the booking by 5 more days
        String newEndDate = "2024-12-10";
        bookingManager.extendBooking(bookingId, newEndDate);
        
        // Verify booking extension
        Booking extendedBooking = bookingManager.getBookingData(bookingId);
        assertEquals(Date.valueOf(newEndDate), extendedBooking.getEndDate(), "End date should be updated");
        assertEquals("active", extendedBooking.getStatus(), "Status should remain active");
        assertEquals(9 * 200.0, extendedBooking.getTotalPrice(), "Total price should be updated for 9 nights");
    }

    /**
     * Test room upgrade
     * Verifies that changing from STANDARD to SUITE updates the booking and prices correctly
     */
    @Test
    public void testUpgradeRoom() {
        // First create a booking with STANDARD room
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room originalRoom = new Room(TEST_ROOM_ID, RoomType.STANDARD, TEST_HOTEL_ID); // $100 per night
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, originalRoom, user, TEST_HOTEL_ID, "active");
        String bookingId = "BKG-1";
        
        // Upgrade to SUITE
        Room newRoom = new Room("RM-102", RoomType.SUITE, TEST_HOTEL_ID); // $200 per night
        bookingManager.changeRoom(bookingId, newRoom);
        
        // Verify room upgrade
        Booking updatedBooking = bookingManager.getBookingData(bookingId);
        assertEquals("RM-102", updatedBooking.getRoom().getRoomID(), "Room ID should be updated");
        assertEquals(200.0, updatedBooking.getRoom().getPrice(), "Room price should be updated to suite rate");
        assertEquals(4 * 200.0, updatedBooking.getTotalPrice(), "Total price should be updated for suite rate");
    }

    /**
     * Test invoice display
     * Verifies that invoice generation includes all required fields and correct calculations
     */
    @Test
    public void testDisplayInvoice() {
        // Create a booking with PREMIUM room
        Date startDate = Date.valueOf("2024-12-01");
        Date endDate = Date.valueOf("2024-12-05");
        Room room = new Room(TEST_ROOM_ID, RoomType.PREMIUM, TEST_HOTEL_ID); // $150 per night
        User user = new User(TEST_USERNAME, "password", "Test User", "user@test.com", "1234567890");
        
        bookingManager.createBooking(startDate, endDate, TEST_ROOM_ID, TEST_USERNAME, room, user, TEST_HOTEL_ID, "active");
        String bookingId = "BKG-1";
        
        // Create text area for invoice
        JTextArea invoiceTextArea = new JTextArea();
        
        // Generate invoice
        bookingManager.displayInvoice(bookingId, invoiceTextArea);
        
        // Verify invoice content
        String invoiceText = invoiceTextArea.getText();
        assertTrue(invoiceText.contains("INVOICE"), "Invoice should have a header");
        assertTrue(invoiceText.contains(bookingId), "Invoice should contain booking ID");
        assertTrue(invoiceText.contains(TEST_HOTEL_ID), "Invoice should contain hotel ID");
        assertTrue(invoiceText.contains(TEST_USERNAME), "Invoice should contain username");
        assertTrue(invoiceText.contains("150.00"), "Invoice should show correct room rate");
        assertTrue(invoiceText.contains("600.00"), "Invoice should show correct subtotal for 4 nights");
        assertTrue(invoiceText.contains("90.00"), "Invoice should show correct GST amount");
        assertTrue(invoiceText.contains("690.00"), "Invoice should show correct total owing");
    }
    
}
