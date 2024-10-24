package Model;

import DataBase.*;
import java.sql.ResultSet;

public class DbTest {

    public static void main(String[] args) {

        UserManager um = new UserManager();
        User user = um.signIn("Mike_22", "12345");
        if(user==null){
            System.out.println("null");
        }else{
            System.out.println("true");
        }
        
        um.closeConnection();
        
       
    }

}
