/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */
public class UserManagerTest {

    public UserManagerTest() {
    }

    private UserManager userManager;
    private User testUser;
    private static final String TEST_USERNAME = "testuser";

    @BeforeAll
    public static void setUpBeforeAll() {
        UserManager.getInstance().createDatabase();
    }

    @BeforeEach
    public void setUp() {
        userManager = UserManager.getInstance();
        testUser = new Guest(TEST_USERNAME, "password", "Test User", "1234567890", "test@example.com");
        userManager.deleteUser(TEST_USERNAME); // Ensure clean state before each test
    }

    @AfterEach
    public void tearDown() {
        userManager.deleteUser(TEST_USERNAME);
    }

    @Test
    public void testGetInstance() {
        UserManager instance1 = UserManager.getInstance();
        UserManager instance2 = UserManager.getInstance();
        assertSame(instance1, instance2, "Singleton instance should be returned.");
    }

    @Test
    public void testGetUserData_ExistingUser() {
        userManager.registerUser(testUser);
        User retrievedUser = userManager.getUserData(TEST_USERNAME);
        assertNotNull(retrievedUser, "User should be retrieved.");
        assertEquals(TEST_USERNAME, retrievedUser.getUserName(), "Usernames should match.");
        // Add assertions for other fields as needed.
    }

    @Test
    public void testGetUserData_NonExistingUser() {
        User retrievedUser = userManager.getUserData("nonexistent");
        assertNull(retrievedUser, "No user should be retrieved for a nonexistent username.");
    }

    @Test
    public void testSignIn_Success() {
        userManager.registerUser(testUser);
        User loggedInUser = userManager.signIn(TEST_USERNAME, "password");
        assertNotNull(loggedInUser, "Login should be successful.");
        assertEquals(TEST_USERNAME, loggedInUser.getUserName(), "Usernames should match.");
    }

    @Test
    public void testSignIn_InvalidPassword() {
        userManager.registerUser(testUser);
        User loggedInUser = userManager.signIn(TEST_USERNAME, "wrongpassword");
        assertNull(loggedInUser, "Login should fail with invalid password.");
    }

    @Test
    public void testSignIn_NonExistingUser() {
        User loggedInUser = userManager.signIn("nonexistent", "password");
        assertNull(loggedInUser, "Login should fail with nonexistent user.");
    }

    @Test
    public void testRegisterUser() {
        userManager.registerUser(testUser);
        User retrievedUser = userManager.getUserData(TEST_USERNAME);
        assertNotNull(retrievedUser, "User should be registered.");
        assertEquals(TEST_USERNAME, retrievedUser.getUserName(), "Usernames should match.");
    }

    @Test
    public void testUpdateUserData() {
        userManager.registerUser(testUser);
        User updatedUser = new Guest(TEST_USERNAME, "newpassword", "Updated Name", "123456789", "updated@example.com");
        assertTrue(userManager.updateUserData(TEST_USERNAME, updatedUser), "User data should update successfully.");
        User retrievedUser = userManager.getUserData(TEST_USERNAME);
        assertEquals("Updated Name", retrievedUser.getName(), "Name should be updated.");
        assertEquals("newpassword", retrievedUser.getPassword(), "Password should be updated.");
        assertEquals("123456789", retrievedUser.getPhone(), "Phone should be updated.");
        assertEquals("updated@example.com", retrievedUser.getEmail(), "Email should be updated.");

    }

    @Test
    public void testGetCurrentUser_LoggedIn() {
        userManager.registerUser(testUser);
        userManager.signIn(TEST_USERNAME, "password");
        User currentUser = userManager.getCurrentUser();
        assertNotNull(currentUser, "Current user should not be null after login.");
        assertEquals(TEST_USERNAME, currentUser.getUserName(), "Usernames should match.");
    }
}
