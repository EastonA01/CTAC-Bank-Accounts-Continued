package org.example;

import java.util.Scanner;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private int accountNumber;

    // Class constructor with account holder and balance
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountNumber =(int) System.currentTimeMillis();
    }

    // Constructor with no parameters
    public BankAccount() {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);
        // Startup
        System.out.println("Hello world! Welcome to the Bank of Easton!");
        int customerStatus = 0;
        while (customerStatus == 0) {
            // Existing user query
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            switch (choice) {
                case -1:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Let's log you in!");
                    customerStatus = 1;
                    break;
                case 2:
                    System.out.println("Let's make a new account!");
                    customerStatus = 2;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        if (customerStatus == 2) {
            System.out.println("What is the name for the account?");
            String name = scanner.next();
            System.out.println("What is the beginning balance for the account?");
            double balance = scanner.nextDouble();
            System.out.println("Creating account...");
            this.accountHolder = name;
            this.balance = balance;
        } else {
            System.out.println("What is the name for the account?");
            System.out.println("Hello, "+ scanner.next() +"!");
            System.exit(0);
        }
    }



    // Methods
    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdrawal(double amount) {
        this.balance -= amount;
    }

    public void getBalance() {
        System.out.println(this.accountHolder + "'s Account Balance: " + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    // Method to transfer amount to another BankAccount
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (this.balance < amount) {
            System.out.println("Insufficient balance for transfer.");
            return;
        }
        // Deduct amount from this account
        this.balance -= amount;
        // Add amount to target account
        targetAccount.balance += amount;
        System.out.println("Successfully transferred " + amount + " from " + this.accountHolder + " to " + targetAccount.getAccountHolder());
    }
}
