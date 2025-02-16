import java.util.Scanner;
import java.util.InputMismatchException;

// BankAccount class - Handles balance, deposits, and withdrawals
class BankAccount {
    private double balance;
    private final String pin;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean validatePIN(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit Successful: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal Successful: $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

// ATM class - Handles transactions & user interaction
class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("\nWelcome to Secure ATM");
        System.out.println("==============================");

        if (!authenticateUser()) {
            System.out.println("Too many incorrect attempts. Your account is locked.");
            return;
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1️ Withdraw");
            System.out.println("2️ Deposit");
            System.out.println("3️ Check Balance");
            System.out.println("4️ Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> withdrawProcess();
                    case 2 -> depositProcess();
                    case 3 -> System.out.println("Current Balance: $" + account.getBalance());
                    case 4 -> {
                        System.out.println("Thank you for using our ATM. Have a great day!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private boolean authenticateUser() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.next();

            if (account.validatePIN(enteredPin)) {
                System.out.println("PIN Verified. Access Granted.");
                return true;
            } else {
                attempts--;
                System.out.println("Invalid PIN! Attempts left: " + attempts);
            }
        }
        return false;
    }

    private void withdrawProcess() {
        System.out.print("Enter withdrawal amount: $");
        try {
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next();
        }
    }

    private void depositProcess() {
        System.out.print("Enter deposit amount: $");
        try {
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid amount.");
            scanner.next();
        }
    }
}

// Main class to run the ATM
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00, "1234"); // Balance: $1000, PIN: 1234
        ATM atmMachine = new ATM(userAccount);
        atmMachine.start();
    }
}
