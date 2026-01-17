package com.banking_system.ui;
import com.banking_system.Utils.InputHandle;

import java.util.Scanner;

import com.banking_system.model.Account;

public class Menu {
    

    public static int login(Scanner sc){
        int mainChoice = 0;
        System.out.println("1. Login");
        System.out.println("2. Create account");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        try {
            mainChoice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error try again: ");
            return login(sc);
        } 
        return mainChoice;
    }

    public static int choice(Account ConnectedAccount,Scanner sc){
        System.out.println("Welcome, " + ConnectedAccount.getownerName());
        System.out.println("1. Deposit\n2. Withdraw\n3. Display Info\n4. Delet Account\n5. Logout");
        System.out.print("Choose an option: ");
        int choice = InputHandle.integerInput(sc);
        return choice;
    }
}
