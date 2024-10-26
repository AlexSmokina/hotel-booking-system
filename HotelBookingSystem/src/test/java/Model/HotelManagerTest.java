/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexander Smokina
 */
public class HotelManagerTest {

    private HotelManager hotelManager;
    private DbManager dbManager;
    private RoomManager roomManager;

    @BeforeEach
    public void setUp() {
        // Initialise managers
        hotelManager = HotelManager.getInstance();
        dbManager = hotelManager.getDbManager();
        roomManager = RoomManager.getInstance();       
        //roomManager.clearRoomData();

        // Create fresh tables
        hotelManager.createDatabase();
    }

    @AfterEach
    public void tearDown() {
        hotelManager.clearHotelData("Test Hotel");
        hotelManager.clearHotelData("Test Hotel Update");
        //roomManager.clearRoomData();
    }

    @Test
    public void testGetInstance() {
        HotelManager instance1 = HotelManager.getInstance();
        HotelManager instance2 = HotelManager.getInstance();

        assertNotNull(instance1, "getInstance should not return null");
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    public void testCreateNewHotel() {
        hotelManager.createNewHotel("Test Hotel", "Test Location", 2, 1, 1);
        Hotel hotel = hotelManager.getHotelData("HTL-1");

        assertNotNull(hotel, "Created hotel should not be null");
        assertEquals("Test Hotel", hotel.getName(), "Hotel name should match");
        assertEquals("Test Location", hotel.getLocation(), "Hotel location should match");
        assertEquals(2, hotel.getNumStandardRooms(), "Number of standard rooms should match");
        assertEquals(1, hotel.getNumPremiumRooms(), "Number of premium rooms should match");
        assertEquals(1, hotel.getNumSuites(), "Number of suites should match");
    }

    @Test
    public void testCreateNewHotelWithInvalidData() {
        // Test with null values
        hotelManager.createNewHotel(null, null, 0, 0, 0);
        Hotel hotel = hotelManager.getHotelData("HTL-1");
        assertNull(hotel, "Hotel should not be created with null data");

        // Test with negative room numbers
        hotelManager.createNewHotel("Test Hotel", "Location", -1, -1, -1);
        hotel = hotelManager.getHotelData("HTL-1");
        assertNull(hotel, "Hotel should not be created with negative room numbers");
    }

    @Test
    public void testCreateHotelWithZeroRooms() {
        hotelManager.createNewHotel("Zero Room Hotel", "Location", 0, 0, 0);
        Hotel hotel = hotelManager.getHotelData("HTL-1");

        assertNotNull(hotel, "Hotel should be created with zero rooms");
        assertEquals(0, hotel.getNumStandardRooms(), "Should have zero standard rooms");
        assertEquals(0, hotel.getNumPremiumRooms(), "Should have zero premium rooms");
        assertEquals(0, hotel.getNumSuites(), "Should have zero suites");
    }

    @Test
    public void testUpdateHotelDetails() {
        // Create initial hotel
        hotelManager.createNewHotel("Test Hotel", "Original Location", 1, 1, 1);

        // Update hotel details
        hotelManager.updateHotelDetails("HTL-1", "Test Hotel Update", "Updated Location");
        Hotel hotel = hotelManager.getHotelData("HTL-1");

        assertNotNull(hotel, "Updated hotel should not be null");
        assertEquals("Test Hotel Update", hotel.getName(), "Hotel name should be updated");
        assertEquals("Updated Location", hotel.getLocation(), "Hotel location should be updated");

        // Verify room counts remain unchanged
        assertEquals(1, hotel.getNumStandardRooms(), "Standard rooms should remain unchanged");
        assertEquals(1, hotel.getNumPremiumRooms(), "Premium rooms should remain unchanged");
        assertEquals(1, hotel.getNumSuites(), "Suites should remain unchanged");
    }

    @Test
    public void testUpdateHotelWithNullValues() {
        // Create a valid hotel first
        hotelManager.createNewHotel("Test Hotel", "Original Location", 1, 1, 1);

        // Try to update with null values
        hotelManager.updateHotelDetails("HTL-1", null, null);

        // Verify original values remain unchanged
        Hotel hotel = hotelManager.getHotelData("HTL-1");
        assertEquals("Test Hotel", hotel.getName(), "Name should remain unchanged");
        assertEquals("Original Location", hotel.getLocation(), "Location should remain unchanged");
    }

    @Test
    public void testUpdateNonExistentHotel() {
        hotelManager.updateHotelDetails("NONEXISTENT-ID", "New Name", "New Location");
        Hotel hotel = hotelManager.getHotelData("NONEXISTENT-ID");
        assertNull(hotel, "Non-existent hotel should not be updated");
    }

//    @Test
//    public void testIdGenerator() {
//        String firstId = hotelManager.idGenerator();
//        assertEquals("HTL-1", firstId, "First generated ID should be HTL-1");
//
//        hotelManager.createNewHotel("Test Hotel", "Test Location", 1, 1, 1);
//
//        String secondId = hotelManager.idGenerator();
//        assertEquals("HTL-2", secondId, "Second generated ID should be HTL-2");
//    }
//
//    @Test
//    public void testHotelIdSequence() {
//        hotelManager.createNewHotel("Hotel 1", "Location 1", 1, 1, 1);
//        hotelManager.createNewHotel("Hotel 2", "Location 2", 1, 1, 1);
//        hotelManager.createNewHotel("Hotel 3", "Location 3", 1, 1, 1);
//
//        assertNotNull(hotelManager.getHotelData("HTL-1"), "First hotel should have ID HTL-1");
//        assertNotNull(hotelManager.getHotelData("HTL-2"), "Second hotel should have ID HTL-2");
//        assertNotNull(hotelManager.getHotelData("HTL-3"), "Third hotel should have ID HTL-3");
//    }

    @Test
    public void testInsertInitialData() {
        hotelManager.insertInitialData();

        Hotel hotel1 = hotelManager.getHotelData("HTL-1");
        assertNotNull(hotel1, "First initial hotel should exist");
        assertEquals("Auckland Skyline", hotel1.getName());
        assertEquals("Auckland", hotel1.getLocation());
        assertEquals(2, hotel1.getNumStandardRooms());
        assertEquals(2, hotel1.getNumPremiumRooms());
        assertEquals(1, hotel1.getNumSuites());

        Hotel hotel2 = hotelManager.getHotelData("HTL-2");
        assertNotNull(hotel2, "Second initial hotel should exist");
        assertEquals("Queenstown Grand", hotel2.getName());
        assertEquals("Queenstown", hotel2.getLocation());
        assertEquals(1, hotel2.getNumStandardRooms());
        assertEquals(1, hotel2.getNumPremiumRooms());
        assertEquals(1, hotel2.getNumSuites());
    }

    @Test
    public void testViewHotels() {
        hotelManager.createNewHotel("Test Hotel", "Location 1", 1, 1, 1);
        hotelManager.createNewHotel("Test Hotel Update", "Location 2", 2, 2, 2);

        String hotelList = hotelManager.viewHotels();

        assertTrue(hotelList.contains("Test Hotel"), "Should contain first hotel name");
        assertTrue(hotelList.contains("Location 1"), "Should contain first hotel location");
        assertTrue(hotelList.contains("Test Hotel Update"), "Should contain second hotel name");
        assertTrue(hotelList.contains("Location 2"), "Should contain second hotel location");
        assertTrue(hotelList.contains("HTL-1"), "Should contain first hotel ID");
        assertTrue(hotelList.contains("HTL-2"), "Should contain second hotel ID");
    }

    @Test
    public void testClearHotelData() {
        hotelManager.createNewHotel("Test Hotel", "Test Location", 1, 1, 1);
        assertNotNull(hotelManager.getHotelData("HTL-1"), "Hotel should be created");

        hotelManager.clearHotelData("Test Hotel");

        Hotel hotel = hotelManager.getHotelData("HTL-1");
        assertNull(hotel, "Hotel data should be cleared");

        String hotelList = hotelManager.viewHotels();
        assertFalse(hotelList.contains("Test Hotel"), "Hotel list should not contain cleared hotel");
    }

    @Test
    public void testGetHotelDataWithInvalidId() {
        Hotel hotel = hotelManager.getHotelData("INVALID-ID");
        assertNull(hotel, "Should return null for invalid hotel ID");
    }

    @Test
    public void testDatabaseConnection() {
        assertNotNull(dbManager, "Database manager should not be null");
        assertNotNull(dbManager.getConnection(), "Database connection should not be null");
    }
}
