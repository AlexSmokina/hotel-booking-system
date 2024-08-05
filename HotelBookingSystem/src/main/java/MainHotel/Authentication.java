/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.util.HashMap;

/**
 *
 * @author minthihakoko
 */
public class Authentication {

    private HashMap<String, User> map;

    public Authentication() {
        map = new HashMap<>();
    }

    public boolean occupied(String userName) {
        return map.containsKey(userName);
    }

    public String registerUser(String userName, String password, String name, String phone, String email, UserType type) {
        User newUser;
        if (type == UserType.GUEST) {
            newUser = new Guest(userName, password, name, phone, email);

        } else {
            newUser = new Staff(userName, password, name, phone, email);

        }
        map.put(userName, newUser);
        return "User registered successfully";
        

    }

}
