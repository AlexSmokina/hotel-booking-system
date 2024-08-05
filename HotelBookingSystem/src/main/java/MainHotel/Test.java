/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainHotel;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author minthihakoko
 */
public class Test {

    public static void main(String[] args) {
        UserType type = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username:");
        String userName = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();

        System.out.println("Choose User Type(1. Guest, 2. Staff)");

        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                type = UserType.GUEST;
                break;
            case 2:
                type = UserType.STAFF;
                break;
            default:
                System.out.println("Wrong input!, Try Again");
                break;
        }
 

        
        System.out.println("Enter email:");
        String email = scan.nextLine();
        System.out.println("Enter name:");
        String name = scan.nextLine();
        System.out.println("Enter phone:");
        String phone = scan.nextLine();

        Registeration newRegisteration = new Registeration(userName, password, name, phone, email, type);

        System.out.println(newRegisteration.newUser.getType());
        
        scan.close();
    }
}
