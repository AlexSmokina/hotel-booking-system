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
public class Registeration {

    private String userName;
    private String password;
    public User newUser;
    private HashMap<String, User> map;

    public Registeration() {
        map = new HashMap<>();
    }

    public boolean occupied(String userName) {
        return map.containsKey(userName);
    }

    public void registerUser(String userName, String password, String name, String phone, String email, UserType type) {
        if (!occupied(userName)) {
            this.userName = userName;
            this.password = password;
            if (type == UserType.GUEST) {
                newUser = new Guest(name, phone, email);

            } else {
                newUser = new Staff(name, phone, email);

            }
            map.put(userName, newUser);
        }

    }

}
