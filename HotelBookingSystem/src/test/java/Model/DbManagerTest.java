/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */
public class DbManagerTest {

    public DbManagerTest() {
    }

    private static DbManager dbManager;
    private static final String TEST_TABLE = "TEST_TABLE";

    @BeforeAll
    public static void setUpClass() {
        // Initialise the DbManager instance before all tests
        dbManager = DbManager.getInstance();
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up by closing connections
        if (dbManager != null) {
            dbManager.closeConnections();
        }
    }

    @BeforeEach
    public void setUp() {
        // Drop the test table if it already exists
        if (dbManager.doesTableExist(TEST_TABLE)) {
            dbManager.updateDB("DROP TABLE " + TEST_TABLE);
        }
        // Create a new test table before each test
        String createTableSQL = "CREATE TABLE " + TEST_TABLE + " ("
                + "ID INTEGER PRIMARY KEY, "
                + "NAME VARCHAR(50))";
        dbManager.updateDB(createTableSQL);
    }

    @AfterEach
    public void tearDown() {
        // Drop the test table after each test
        if (dbManager.doesTableExist(TEST_TABLE)) {
            dbManager.updateDB("DROP TABLE " + TEST_TABLE);
        }
    }

    @Test
    public void testGetInstance() {
        System.out.println("Testing getInstance");
        // Get the first instance of DbManager
        DbManager instance1 = DbManager.getInstance();
        assertNotNull(instance1, "getInstance should return non-null instance");
        // Get the second instance of DbManager and check that it is the same as the first
        DbManager instance2 = DbManager.getInstance();
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    public void testGetConnection() {
        System.out.println("Testing getConnection");
        // Get the connection from DbManager
        Connection conn = dbManager.getConnection();
        assertNotNull(conn, "Connection should not be null");
        // Check that the connection is open
        try {
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEstablishConnection() {
        System.out.println("Testing establishConnection");
        // Establish a new connection
        dbManager.establishConnection();
        Connection conn = dbManager.getConnection();
        assertNotNull(conn, "Connection should be established");
        // Verify that the connection is open
        try {
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testQueryDB() {
        System.out.println("Testing queryDB");
        // Insert test data into the test table
        dbManager.updateDB("INSERT INTO " + TEST_TABLE + " (ID, NAME) VALUES (1, 'Test Name')");
        // Query the inserted data
        String querySQL = "SELECT * FROM " + TEST_TABLE + " WHERE ID = 1";
        ResultSet rs = dbManager.queryDB(querySQL);
        // Verify that the correct data was retrieved
        try {
            assertTrue(rs.next(), "Query should return results");
            assertEquals("Test Name", rs.getString("NAME"), "Retrieved name should match inserted name");
            rs.close();
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateDB() {
        System.out.println("Testing updateDB");
        // Insert test data if not already present
        ResultSet rs = dbManager.queryDB("SELECT * FROM " + TEST_TABLE + " WHERE ID = 2");
        try {
            if (!rs.next()) {
                String insertSQL = "INSERT INTO " + TEST_TABLE + " (ID, NAME) VALUES (2, 'Update Test')";
                dbManager.updateDB(insertSQL);
            }
            rs.close();
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
        // Update the existing record
        String updateSQL = "UPDATE " + TEST_TABLE + " SET NAME = 'Updated Name' WHERE ID = 2";
        dbManager.updateDB(updateSQL);
        // Verify that the record was updated
        rs = dbManager.queryDB("SELECT * FROM " + TEST_TABLE + " WHERE ID = 2");
        try {
            assertTrue(rs.next(), "Updated record should exist");
            assertEquals("Updated Name", rs.getString("NAME"), "Name should be updated");
            rs.close();
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testDoesTableExist() {
        System.out.println("Testing doesTableExist");
        // Check that the test table exists
        assertTrue(dbManager.doesTableExist(TEST_TABLE), "Test table should exist");

        // Check that a non-existent table does not exist
        assertFalse(dbManager.doesTableExist("NONEXISTENT_TABLE"), "Non-existent table should return false");
    }

    @Test
    public void testCloseConnections() {
        System.out.println("Testing closeConnections");

        // Get the current connection
        Connection conn = dbManager.getConnection();
        // Close the connection
        dbManager.closeConnections();
        // Verify that the connection is closed
        try {
            assertTrue(conn.isClosed(), "Connection should be closed");
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
        // Re-establish the connection for other tests
        dbManager.establishConnection();
    }

    @Test
    public void testQueryDB_InvalidSQL() {
        System.out.println("Testing queryDB with invalid SQL");
        // Run a query on a non-existent table and verify the result is null
        ResultSet rs = dbManager.queryDB("SELECT * FROM NONEXISTENT_TABLE");
        assertNull(rs, "Invalid query should return null");
    }

    @Test
    public void testUpdateDB_InvalidSQL() {
        System.out.println("Testing updateDB with invalid SQL");
        // Attempt an invalid update and ensure no exceptions are thrown
        dbManager.updateDB("UPDATE NONEXISTENT_TABLE SET COLUMN = 'value'");
        // If no exceptions were thrown, the test is successful
        assertTrue(true, "Invalid update should not throw exception");
    }

}
