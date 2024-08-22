/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author minthihakoko
 */
public class UserManger implements FileHandler{
    private String fileName;
    private Map<String, User> usersData;
    
    public UserManger(String fileName){
        this.fileName = fileName;
        usersData = new HashMap<>();   
    }

    @Override
    public void loadData() {
        usersData.clear();
        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            bufferReader.readLine(); 
            String line;
            while((line=bufferReader.readLine())!=null){
                User user = parseUser(line);
                usersData.put(user.getUserName(), user);  
            }
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
        }
    }

    @Override
    public void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("username,password,name,phone,email,userType");
            for(User user : usersData.values()){
                printWriter.println(dataToString(user));
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }
    
    private User parseUser(String line){
        String[] parts = line.split(",");
        User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
        user.setType(UserType.valueOf(parts[5]));
        
        return user;    
    }
    
    private String dataToString(User user){
        String output;
        output = String.format("%s,%s,%s,%s,%s,%s", user.getUserName(), user.getPassword(),
                user.getName(),user.getPhone(),user.getEmail(),user.getType());
        return output;
    }
    
    public void addUserData(User newUser){
        usersData.put(newUser.getUserName(), newUser);
    }
    
    public User getUserData(String username){
        return usersData.get(username);
    }
    
    public boolean updateUserData(String username, User newUserData){
        if(!usersData.containsKey(username)){
            return false;
        }else{
            usersData.put(username, newUserData);
            return true;  
        } 
    }
    
    public Set<String> getAllUsernames() {
        return usersData.keySet();
    }
       
}
