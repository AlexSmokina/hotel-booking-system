/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

/**
 *
 * @author minthihakoko
 */
public interface DatabaseCreator {
    void createDatabase();
    default void insertInitialData() {
        // Default empty implementation
    }

    
}
