package com.banking_system.Utils;

import java.util.Scanner;

public class InputHandle {
    public static int integerInput(Scanner sc){
    int number = 0;
    while(true){
        try {
            number = Integer.parseInt(sc.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.print("Try enter a number: ");
        }
    }
    return number;
}
    public static double doubleInput(Scanner sc){
    double number = 0;
    while(true){
        try {
            number = Double.parseDouble(sc.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.print("Try enter a number: ");
        }
    }
    return number;
}

}
