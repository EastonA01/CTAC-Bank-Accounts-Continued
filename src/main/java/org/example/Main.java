package org.example;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    // Array list
    public static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        // Faux account to transfer money from
        accounts.add(new BankAccount("John Doe", 500.0));
        BankAccount acc = new BankAccount(); // Create accounts section
        accounts.add(acc);
        MainMenu(accounts);
    }



    public static void MainMenu(ArrayList<BankAccount> accounts) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bank of Easton!");

        // Display account options
        System.out.println("Please select your account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getAccountHolder());
            System.out.println("Acc #: "+ accounts.get(i).getAccountNumber());
        }
        System.out.println("Enter the number of your account: (I.e If John Doe, type 1) ");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Invalid choice, please restart the application.");
            System.exit(0);
        }

        BankAccount selectedAccount = accounts.get(choice - 1);
        System.out.println("Hello, " + selectedAccount.getAccountHolder() + "!");
        while(true){
            // Account Actions
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance\n2. To make a withdraw\n3. To make a deposit\n4. To make a transfer to another account\n0. To exit.");
            choice = scanner.nextInt();
            switch(choice){
                case 1:
                    selectedAccount.getBalance();
                    break;
                case 2:
                    System.out.println("Enter the amount to withdraw");
                    int withdraw = scanner.nextInt();
                    selectedAccount.withdrawal(withdraw);
                    System.out.println("Withdraw of "+withdraw+" from "+selectedAccount.getAccountHolder()+" successful.");
                    break;
                case 3:
                    System.out.println("Enter the amount to transfer to deposit");
                    int deposit = scanner.nextInt();
                    selectedAccount.deposit(deposit);
                    System.out.println("Deposit of "+deposit+" successful.");
                    break;
                case 4:
                    try {
                        System.out.println("Please enter the account number to transfer to:");
                        int transferAccountNo = scanner.nextInt();
                        BankAccount targetAccount = null;

                        // Check if acc # exists in records
                        for (BankAccount account : accounts) {
                            if (account.getAccountNumber() == transferAccountNo) {
                                targetAccount = account;
                                break;
                            }
                        }

                        if (targetAccount == null) {
                            throw new Exception("Account number " + transferAccountNo + " not found.");
                        }

                        // Acquire bank object of account to transfer funds to
                        System.out.println("Enter the amount to transfer:");
                        int transferAmount = scanner.nextInt();
                        selectedAccount.transfer(targetAccount, transferAmount);

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }

    }
}
