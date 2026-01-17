package com.banking_system.dao;

import java.util.List;

import com.banking_system.model.Account;

public interface AccountDao {
    List<Account> getAllAccounts();
    void createAccount(String ownerName, double initialBalance, int password);
    Account getOneAccount(String ownerName, int password);
    void updateBalance(Account account, double newBalance);
    void deleteAccount(String ownerName, int password);
} 
