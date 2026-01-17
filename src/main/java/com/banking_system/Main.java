package com.banking_system;
import com.banking_system.Utils.InputHandle;
import com.banking_system.ui.Menu;
import com.banking_system.dao.DBConnection;
import com.banking_system.model.Account;
import com.banking_system.dao.AccountDao;
import com.banking_system.dao.AccountDaoImplementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import antlr.MismatchedTokenException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System");
        Scanner sc = new Scanner(System.in);
        AccountDao accountDao = new AccountDaoImplementation();
        boolean GlobalStay = true;
        while (GlobalStay) {
            int mainChoice = Menu.login(sc);
            switch (mainChoice) {
                case 1:
                    System.out.print("Enter your owner name: ");
                    String ownerName = sc.nextLine();
                    System.out.print("Enter your password: ");
                    int password = InputHandle.integerInput(sc);
                    Account ConnectedAccount = accountDao.getOneAccount(ownerName, password);
                    if (ConnectedAccount != null) {
                        boolean stay = true;
                        while (stay) {
                            int choice = Menu.choice(ConnectedAccount, sc);
                            switch (choice) {
                                case 1:
                                    System.out.print("Enter deposit amount: ");
                                    double depositAmount = InputHandle.doubleInput(sc);
                                    ConnectedAccount.deposit(depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter withdrawal amount: ");
                                    double withdrawAmount = InputHandle.doubleInput(sc);
                                    ConnectedAccount.withdraw(withdrawAmount);
                                    break;
                                case 3:
                                    ConnectedAccount.displayInfo();
                                    break;
                                case 4:
                                    accountDao.deleteAccount(ConnectedAccount.getownerName(), ConnectedAccount.getPassword());
                                    stay = false;
                                    break;
                                case 5:
                                    stay = false;
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid owner name or password.");
                    }
                    break;
                case 2:
                    System.out.print("Enter your owner name: ");
                    String newOwnerName = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = InputHandle.doubleInput(sc);
                    System.out.print("Set your password (number only): ");
                    int newPassword = InputHandle.integerInput(sc);
                    accountDao.createAccount(newOwnerName, initialBalance, newPassword);
                    break;
                case 3:
                    GlobalStay = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                }
        
        }
        sc.close();
        
    }
}