/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */
public class RoomManagerTest {

    private RoomManager roomManager;
    private HotelManager hotelManager;
    private DbManager dbManager;
    private String testHotel = "Test Hotel 1";

    @BeforeEach
    public void setUp() {
        try {
            // Initialise DbManager first
            dbManager = DbManager.getInstance();
            if (dbManager.getConnection() == null) {
                dbManager.establishConnection();
            }

            // Initialising Managers
            hotelManager = HotelManager.getInstance();
            roomManager = RoomManager.getInstance();

            // Creating database tables if they don't exist
            hotelManager.createDatabase();
            roomManager.createDatabase();

            // Insert initial data
            hotelManager.insertInitialData();
            roomManager.insertInitialData();

        } catch (Exception e) {
            System.err.println("Error in setup: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {

            String hotelID = hotelManager.getHotelIDByName(testHotel);
            roomManager.clearRoomData(hotelID);
            hotelManager.clearHotelData(testHotel);

        } catch (Exception e) {
            System.err.println("Error in teardown: " + e.getMessage());
        }
    }

    /**
     * Test getting room instance
     */
    @Test
    public void testGetInstance() {
        assertNotNull(roomManager, "RoomManager instance should not be null");
        RoomManager instance2 = RoomManager.getInstance();
        assertSame(roomManager, instance2, "getInstance should return the same instance");
    }

    /**
     * Test creating new room data
     */
    @Test
    public void testCreateRoom() {
        try {
            hotelManager.createNewHotel(testHotel, "Location 1", 0, 1, 1);
            String testHotelID_1 = hotelManager.getHotelIDByName(testHotel);
            roomManager.createRoom("standard", testHotelID_1);
            Room room = roomManager.getRoomData("RM/STD-1", testHotelID_1);

            assertNotNull(room, "Created room should not be null");
            assertEquals(RoomType.STANDARD.name(), room.getRoomType(), "Room type should be STANDARD");
            assertEquals(testHotelID_1, room.getHotelID(), "Hotel ID should match");
            assertFalse(room.isBooked(), "New room should be available");
        } catch (Exception e) {
            fail("Room creation failed: " + e.getMessage());
        }
    }

    /**
     * Test updating room data
     */
    @Test
    public void testUpdateRoomData() {
        hotelManager.createNewHotel(testHotel, "Location 1", 0, 1, 1);
        String testHotelID_1 = hotelManager.getHotelIDByName(testHotel);
        roomManager.createRoom("standard", testHotelID_1);
        Room room = roomManager.getRoomData("RM/STD-1", testHotelID_1);

        room.setIsBooked(true);

        boolean updateSuccess = roomManager.updateRoomData("RM/STD-1", room);
        assertTrue(updateSuccess, "Room update should be successful");

        Room updatedRoom = roomManager.getRoomData("RM/STD-1", testHotelID_1);
        assertTrue(updatedRoom.isBooked(), "Room should be marked as booked");
    }

    /**
     * Test to filter room by availability date
     */
    @Test
    public void testFilterByDate() {
        try {
            hotelManager.createNewHotel(testHotel, "Original Location", 1, 1, 1);
            String hotelID = hotelManager.getHotelIDByName(testHotel);
            // Create test rooms with different dates and availability
            roomManager.createRoom("standard", hotelID);  // First room
            roomManager.createRoom("premium", hotelID);   // Second room

            // Get the created rooms
            Room room1 = roomManager.getRoomData("RM/STD-1", hotelID);
            Room room2 = roomManager.getRoomData("RM/PRM-1", hotelID);

            // Set different availability dates for the rooms
            room1.setAvailabilityDate("2024-10-15");
            room2.setAvailabilityDate("2024-10-20");

            // Mark one room as booked
            room2.setIsBooked(true);

            // Update the rooms in the database
            roomManager.updateRoomData("RM/STD-1", room1);
            roomManager.updateRoomData("RM/PRM-1", room2);

            // Test filtering with a date before both rooms' availability
            List<Room> earlyDateRooms = roomManager.filterByDate("2024-10-01", testHotel);
            assertEquals(0, earlyDateRooms.size(), "Should find no rooms before their availability dates");

            // Test filtering with a date between the two rooms' availability
            List<Room> midDateRooms = roomManager.filterByDate("2024-10-17", testHotel);
            assertEquals(1, midDateRooms.size(), "Should find only the first room");
            assertEquals("STANDARD", midDateRooms.get(0).getRoomType(), "Should be the standard room");

            // Test filtering with a date after both rooms' availability
            // Note: Second room is booked, so it shouldn't appear in results
            List<Room> lateDateRooms = roomManager.filterByDate("2024-10-25", testHotel);
            assertEquals(1, lateDateRooms.size(), "Should find only the unbooked room");
            assertEquals("STANDARD", lateDateRooms.get(0).getRoomType(), "Should be the standard room");

            // Test with non-existent hotel
            List<Room> nonExistentHotelRooms = roomManager.filterByDate("2024-10-15", "NonExistentHotel");
            assertTrue(nonExistentHotelRooms.isEmpty(), "Should return empty list for non-existent hotel");

        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }
}
