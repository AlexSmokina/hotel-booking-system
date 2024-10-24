package Model;

public class DbTest {

    public static void main(String[] args) {

        UserManager um = new UserManager();
        um.createDatabase();
        User newUser = new User("Mike_22", "12345", "Mike", "02153535", "example@gmail.com");
        
        newUser.setType(UserType.STAFF);
        um.registerUser(newUser);
        um.closeConnection();

    }

}
