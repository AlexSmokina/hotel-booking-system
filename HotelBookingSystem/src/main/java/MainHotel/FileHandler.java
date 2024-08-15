/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.util.Map;

/**
 *
 * @author minthihakoko
 * @param <E>
 */
public interface FileHandler<E> {
    void load(String filePath, Map<String,E> map);
    void save(String filePath, Map<String,E> map);
}
