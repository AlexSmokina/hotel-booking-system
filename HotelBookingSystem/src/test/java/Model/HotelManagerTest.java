/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */
public class HotelManagerTest {

    private HotelManager hotelManager;
    private DbManager dbManager;

    @BeforeEach
    public void setUp() {
        // Initialize HotelManager instance before each test
        hotelManager = HotelManager.getInstance();
        dbManager = hotelManager.getDbManager();

        // Clear existing data instead of dropping tables
        clearHotelData();

        // Ensure tables exist
        hotelManager.createDatabase();
    }

    @AfterEach
    public void tearDown() {
        // Clear data without dropping tables
        clearHotelData();
    }

    private void clearHotelData() {
        try {
            // Delete all records instead of dropping the table
            dbManager.updateDB("DELETE FROM HOTEL");
        } catch (Exception e) {
            System.out.println("Error clearing hotel data: " + e.getMessage());
        }
    }

    /**
     * Test singleton pattern implementation
     */
    @Test
    public void testGetInstance() {
        HotelManager instance1 = HotelManager.getInstance();
        HotelManager instance2 = HotelManager.getInstance();

        assertNotNull(instance1, "getInstance should not return null");
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    /**
     * Test creating a new hotel
     */
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

    /**
     * Test updating hotel details
     */
    @Test
    public void testUpdateHotelDetails() {
        hotelManager.createNewHotel("Original Name", "Original Location", 1, 1, 1);

        hotelManager.updateHotelDetails("HTL-1", "Updated Name", "Updated Location");

        Hotel hotel = hotelManager.getHotelData("HTL-1");

        assertNotNull(hotel, "Updated hotel should not be null");
        assertEquals("Updated Name", hotel.getName(), "Hotel name should be updated");
        assertEquals("Updated Location", hotel.getLocation(), "Hotel location should be updated");
    }

    /**
     * Test hotel ID generation
     */
    @Test
    public void testIdGenerator() {
        String firstId = hotelManager.idGenerator();
        assertEquals("HTL-1", firstId, "First generated ID should be HTL-1");

        hotelManager.createNewHotel("Test Hotel", "Test Location", 1, 1, 1);

        String secondId = hotelManager.idGenerator();
        assertEquals("HTL-2", secondId, "Second generated ID should be HTL-2");
    }

    /**
     * Test initial data insertion
     */
    @Test
    public void testInsertInitialData() {
        hotelManager.insertInitialData();

        Hotel hotel1 = hotelManager.getHotelData("HTL-1");
        assertNotNull(hotel1, "First initial hotel should exist");
        assertEquals("Auckland Skyline", hotel1.getName());
        assertEquals("Auckland", hotel1.getLocation());

        Hotel hotel2 = hotelManager.getHotelData("HTL-2");
        assertNotNull(hotel2, "Second initial hotel should exist");
        assertEquals("Queenstown Grand", hotel2.getName());
        assertEquals("Queenstown", hotel2.getLocation());
    }

}
