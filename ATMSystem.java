import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Define Account class
class Account {
    private String pin;
    private double balance;

    public Account(String pin, double balance) {
        this.pin = pin;
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// ATM class
public class ATMSystem {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public ATMSystem() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to add new account
    public void addAccount(String accountNumber, String pin, double initialBalance) {
        accounts.put(accountNumber, new Account(pin, initialBalance));
    }

    // Method to authenticate user
    public boolean authenticateUser(String accountNumber, String enteredPin) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            return account.getPin().equals(enteredPin);
        }
        return false;
    }

    // Main menu display method
    public void displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("1. Withdrawal");
        System.out.println("2. Deposit");
        System.out.println("3. Balance Inquiry");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to handle withdrawal
    public void withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }
        }
    }

    // Method to handle deposit
    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful. Current balance: " + account.getBalance());
        }
    }

    // Method to handle balance inquiry
    public void checkBalance(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            System.out.println("Current balance: " + account.getBalance());
        }
    }

    public static void main(String[] args) {
        ATMSystem atm = new ATMSystem();
        atm.addAccount("12345", "1234", 1000.0); // Example account creation

        // Example usage flow
        boolean authenticated = false;
        String accountNumber = null, pin;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM");

        // Authentication loop
        while (!authenticated) {
            System.out.print("Enter your account number: ");
            accountNumber = scanner.nextLine();
            System.out.print("Enter your PIN: ");
            pin = scanner.nextLine();

            authenticated = atm.authenticateUser(accountNumber, pin);

            if (!authenticated) {
                System.out.println("Invalid account number or PIN. Please try again.");
            }
        }

        // Display main menu
        int choice;
        do {
            atm.displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: // Withdrawal
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(accountNumber, withdrawAmount);
                    break;
                case 2: // Deposit
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(accountNumber, depositAmount);
                    break;
                case 3: // Balance Inquiry
                    atm.checkBalance(accountNumber);
                    break;
                case 4: // Exit
                    System.out.println("Exiting ATM. Thank you for using our service.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
