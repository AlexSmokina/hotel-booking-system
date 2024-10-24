package Model;

import DataBase.*;
import java.sql.ResultSet;

public class DbTest {

    public static void main(String[] args) {

        UserManager um = new UserManager();
        um.createUserDB();
        User newUser = new User("Mike_22", "12345", "Mike", "0219324", "example@gmail.com");
        newUser.setType(UserType.GUEST);
        um.registerUser(newUser);
        
       
    }

}
