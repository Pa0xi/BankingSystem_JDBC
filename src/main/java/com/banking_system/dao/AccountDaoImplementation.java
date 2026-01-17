package com.banking_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.banking_system.model.Account;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class AccountDaoImplementation implements AccountDao {

    @Override
    public java.util.List<Account> getAllAccounts() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Failed to establish database connection.");
            return null;
        }
        // Implementation to retrieve all accounts from the database goes here
        String sql = "SELECT * FROM accounts";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            java.util.List<Account> accounts = new java.util.ArrayList<>();
            while (resultSet.next()) {
                String ownerName = resultSet.getString("account_owner");
                double balance = resultSet.getDouble("balance");
                int password = resultSet.getInt("password");
                accounts.add(new Account(ownerName, balance, password));
            }
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createAccount(String ownerName, double initialBalance, int password) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Failed to establish database connection.");
            return;
        }
        // Implementation to create account in the database goes here
        String sql = "INSERT INTO accounts (account_owner, balance, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, ownerName);
            preparedStatement.setDouble(2, initialBalance);
            preparedStatement.setInt(3, password);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account created successfully for " + ownerName);
            } else {
                System.out.println("Failed to create account for " + ownerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getOneAccount(String ownerName, int password) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Failed to establish database connection.");
            return null;
        }
        String sql = "SELECT * FROM accounts WHERE account_owner = ? AND password = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, ownerName);
            preparedStatement.setInt(2, password);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                return new Account(ownerName, balance, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBalance(Account account, double newBalance) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Failed to establish database connection.");
            return;
        }
        String sql = "UPDATE accounts SET balance = ? WHERE account_owner = ? AND password = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, account.getownerName());
            preparedStatement.setInt(3, account.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully for " + account.getownerName());
            } else {
                System.out.println("Failed to update balance for " + account.getownerName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(String ownerName, int password) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            System.out.println("Failed to establish database connection.");
            return;
        }
        String sql = "DELETE FROM accounts WHERE account_owner = ? AND password = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, ownerName);
            preparedStatement.setInt(2, password);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully: " + ownerName);
            } else {
                System.out.println("Failed to delete account with: " + ownerName);
            }
        } catch (MySQLSyntaxErrorException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
