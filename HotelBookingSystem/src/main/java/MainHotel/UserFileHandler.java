/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author minthihakoko
 */
public class UserFileHandler implements FileHandler<User> {

    @Override
    public void load(String filePath, Map<String, User> userMap) {
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] values = line.split(",");
                
            }
            
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Error reading from file " + file.getName());
        }
    }

    @Override
    public void save(String filePath, Map<String, User> map) {
    }

}
