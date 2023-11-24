import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

public class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        displayMenu();
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Current balance: " + userAccount.getBalance());
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        userAccount.deposit(amount);
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        userAccount.withdraw(amount);
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance is 1000.0
        ATM atm = new ATM(userAccount);
    }
}
