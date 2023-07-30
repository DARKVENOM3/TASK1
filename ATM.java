import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

class Account {
    private String accountNumber;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void transfer(Account receiver, double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Transfer to " + receiver.accountNumber, amount));
            receiver.deposit(amount);
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println();
    }
}

public class ATM {
    public static void main(String[] args) {
        Account account1 = new Account("1234567890");
        Account account2 = new Account("9876543210");

        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("Welcome to the ATM!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account1.deposit(depositAmount);
                    System.out.println("Deposited $" + depositAmount);
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account1.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    account1.transfer(account2, transferAmount);
                    break;

                case 4:
                    account1.printTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
