package com.banking_system.model;

import com.banking_system.dao.AccountDao;
import com.banking_system.dao.AccountDaoImplementation;

public class Account {

    private String ownerName;
    private double Balance;
    private int password;

    AccountDao accountDao = new AccountDaoImplementation();


    public Account(String ownerName, double initialBalance, int password) {
        this.ownerName = ownerName;
        this.Balance = initialBalance;
        this.password = password;
    }
    public Account(){}
    

    public String getownerName() {
        return ownerName;
    }
    public double getBalance() {
        return Balance;
    }
    public int getPassword(){
        return this.password;
    }
    public boolean checkPassword(int input) {
        return password == input;
    }

    public void deposit(double amount){
        if(amount > 0){
            Balance += amount;
            accountDao.updateBalance(this, Balance);
        }else{
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && amount <= Balance){
            Balance -= amount;
            accountDao.updateBalance(this, Balance);
        }else if(amount > Balance){
            System.out.println("Insufficient funds for withdrawal.");
        }else{
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void displayInfo(){
        System.out.println("Owner: " + ownerName + ", Balance: $" + Balance);
    }

}
