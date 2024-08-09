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
public class Alex_Test {

    public static void main(String[] args) {
        Authentication auth = new Authentication();
        UserType type = null;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {

            String userName;
            while (true) {
                System.out.print("Enter username:");
                userName = scan.nextLine();
                if (auth.occupied(userName)) {
                    System.out.println("Username already existed! Please try again");
                } else {
                    break;
                }
            }

            System.out.print("Enter password:");
            String password = scan.nextLine();
            System.out.print("Enter email:");
            String email = scan.nextLine();
            System.out.print("Enter name:");
            String name = scan.nextLine();
            System.out.print("Enter phone:");
            String phone = scan.nextLine();

            while (true) {
                System.out.print("Choose User Type(1. Guest, 2. Staff):");
                try {
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
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter number!");
                    scan.nextLine();
                }
            }
            String registrationMessage = auth.registerUser(userName, password, name, phone, email, type);

            System.out.println(registrationMessage);

        }
        scan.close();
    }
}
