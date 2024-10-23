/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

/**
 * @author Alexander Smokina & Min Thiha Ko Ko 
 * 
 * The ID interface provides a method for generating unique IDs based on the context.
 * This is typically used for generating IDs for hotels, rooms, and other entities in the system.
 */
interface ID {
    public String idGenerator(Object context); //Abstract Method
}