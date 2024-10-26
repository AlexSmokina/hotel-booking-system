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
    private DbManager dbManager;

    @BeforeEach
    public void setUp() {
        try {
            // Initialise DbManager first
            dbManager = DbManager.getInstance();
            if (dbManager.getConnection() == null) {
                dbManager.establishConnection();
            }

            // Then initialize RoomManager
            roomManager = RoomManager.getInstance();

            // Create database tables if they don't exist
            roomManager.createDatabase();

            // Clear existing data
            roomManager.clearRoomData();

        } catch (Exception e) {
            System.err.println("Error in setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            roomManager.clearRoomData();
        } catch (Exception e) {
            System.err.println("Error in teardown: " + e.getMessage());
            e.printStackTrace();
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
            roomManager.createRoom("standard", "HTL-1");
            Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");

            assertNotNull(room, "Created room should not be null");
            assertEquals(RoomType.STANDARD.name(), room.getRoomType(), "Room type should be STANDARD");
            assertEquals("HTL-1", room.getHotelID(), "Hotel ID should match");
            assertFalse(room.isBooked(), "New room should be available");
        } catch (Exception e) {
            fail("Room creation failed: " + e.getMessage());
        }
    }

    /**
     * Test room ID generation
     */
    @Test
    public void testIdGenerator() {
        Object[] contextStandard = new Object[]{"HTL-1", RoomType.STANDARD};
        Object[] contextPremium = new Object[]{"HTL-1", RoomType.PREMIUM};
        Object[] contextSuite = new Object[]{"HTL-1", RoomType.SUITE};

        String standardId = roomManager.idGenerator(contextStandard);
        String premiumId = roomManager.idGenerator(contextPremium);
        String suiteId = roomManager.idGenerator(contextSuite);

        assertTrue(standardId.startsWith("RM/STD-"), "Standard room ID should start with RM/STD-");
        assertTrue(premiumId.startsWith("RM/PRM-"), "Premium room ID should start with RM/PRM-");
        assertTrue(suiteId.startsWith("RM/SU-"), "Suite ID should start with RM/SU-");
    }

    /**
     * Test updating room data
     */
    @Test
    public void testUpdateRoomData() {
        roomManager.createRoom("Standard", "HTL-1");
        Room room = roomManager.getRoomData("RM/STD-1", "HTL-1");

        room.setIsBooked(true);

        boolean updateSuccess = roomManager.updateRoomData("RM/STD-1", room);
        assertTrue(updateSuccess, "Room update should be successful");

        Room updatedRoom = roomManager.getRoomData("RM/STD-1", "HTL-1");
        assertTrue(updatedRoom.isBooked(), "Room should be marked as booked");
    }

    /**
     * Test filtering rooms by hotel
     */
    @Test
    public void testFilterRoomByHotel() {
        roomManager.createRoom("Standard", "HTL-1");
        roomManager.createRoom("Premium", "HTL-1");
        roomManager.createRoom("Standard", "HTL-2");

        List<Room> hotel1Rooms = roomManager.filterRoomByHotel("HTL-1");
        List<Room> hotel2Rooms = roomManager.filterRoomByHotel("HTL-2");

        assertEquals(2, hotel1Rooms.size(), "Hotel 1 should have 2 rooms");
        assertEquals(1, hotel2Rooms.size(), "Hotel 2 should have 1 room");
    }

    /**
     * Test initial data insertion
     */
    @Test
    public void testInsertInitialData() {
        roomManager.insertInitialData();

        List<Room> hotel1Rooms = roomManager.filterRoomByHotel("HTL-1");
        List<Room> hotel2Rooms = roomManager.filterRoomByHotel("HTL-2");

        assertEquals(5, hotel1Rooms.size(), "Hotel 1 should have 5 rooms");
        assertEquals(3, hotel2Rooms.size(), "Hotel 2 should have 3 rooms");

        long standardRoomsH1 = hotel1Rooms.stream()
            .filter(r -> r.getRoomType().equals("STANDARD"))
            .count();

        assertEquals(2, standardRoomsH1, "Hotel 1 should have 2 standard rooms");
    }
}
