import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount {
    private String accno;
    private String name;
    private String accType;
    private long balance;
    private int num;
    private String address;
    private int totalTransactions = 0;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public void AccountNum(int i) {
        num = 1000 + i;
        accno = "BA" + num;
        System.out.println("Account created, Account no. " + accno);
    }

    public void chng_adrs() {
        System.out.print("Enter New Address: ");
        address = scan.nextLine();
        System.out.println("Address changed, new address: " + address);
    }

    public void openAccount() {
        System.out.print("Enter Account type(Eg. Savings): ");
        accType = scan.next();
        System.out.print("Enter Name: ");
        scan.nextLine();
        name = scan.nextLine();
        System.out.print("Enter Balance: ");
        balance = scan.nextLong();
        System.out.print("Enter Address: ");
        scan.nextLine();
        address = scan.nextLine();
    }

    public void totalTransactions() {
        System.out.println("Total Transactions: " + totalTransactions);
    }

    public void showAccount() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + accno);
        System.out.println("Account type: " + accType);
        System.out.println("Balance: " + balance);
        System.out.println("Address: " + address);
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println();        
    }

    public void deposit() {
        long amount;
        System.out.print("Enter the amount you want to deposit: ");
        amount = scan.nextLong();
        balance += amount;
        totalTransactions++;
        transactionHistory.add("Deposit: +" + amount);
        System.out.println("Balance: " + balance);
    }

    public void withdrawal() {
        long amount;
        System.out.print("Enter the amount you want to withdraw: ");
        amount = scan.nextLong();
        if (balance >= amount && balance>=0) {
            balance -= amount;
            totalTransactions++;
            transactionHistory.add("Withdrawal: -" + amount);
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amount + "\tTransaction failed...!!");
        }
    }

    public void applyInterest(double interestRate) {
        double interest = balance * interestRate / 100;
        balance += interest;
        totalTransactions++;
        transactionHistory.add("Interest Applied: +" + interest);
        System.out.println("Interest applied. New balance: " + balance);
    }

    public boolean search(String accountNumber) {
        if (accno.equals(accountNumber)) {
            showAccount();
            return true;
        }
        return false;
    }
}

public class PRO2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numberOfCustomers;
        do {
            System.out.print("How many customers do you want to input? ");
            try {
                numberOfCustomers = scan.nextInt();
                if (numberOfCustomers <= 0) {
                    System.out.println("Please enter a number greater than zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.next(); // Consume the invalid input to prevent an infinite loop
                numberOfCustomers = 0; // Set numberOfCustomers to 0 to re-enter the loop
            }
        } while (numberOfCustomers <= 0);

        BankAccount[] accounts = new BankAccount[numberOfCustomers];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new BankAccount();
            accounts[i].openAccount();
            accounts[i].AccountNum(i);
        }

        int choice;
        do {
            try {
                System.out.println("\n Enhanced Banking System");
                System.out.println("1. Display all account details");
                System.out.println("2. Search by Account number");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Change Address");
                System.out.println("6. Check Total Transactions");
                System.out.println("7. Apply Interest");
                System.out.println("8. Exit");

                System.out.print("Enter your choice: ");
                System.out.println();
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        for (BankAccount account : accounts) {
                            account.showAccount();
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.print("Enter account no. you want to search: ");
                        String accountNumber = scan.next();
                        boolean found = false;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Account no. : ");
                        accountNumber = scan.next();
                        System.out.println();
                        found = false;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                account.deposit();
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Account No : ");
                        accountNumber = scan.next();
                        found = false;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                account.withdrawal();
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Account No : ");
                        accountNumber = scan.next();
                        found = false;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                account.chng_adrs();
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Account No : ");
                        accountNumber = scan.next();
                        found = true;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                account.totalTransactions();
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 7:
                        System.out.print("Enter Account No : ");
                        accountNumber = scan.next();
                        found = false;
                        for (BankAccount account : accounts) {
                            found = account.search(accountNumber);
                            if (found) {
                                System.out.print("Enter Interest Rate: ");
                                double interestRate = scan.nextDouble();
                                account.applyInterest(interestRate);
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Search failed! Account doesn't exist..!!");
                        }
                        break;
                    case 8:
                        System.out.println("See you soon...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scan.next(); // Consume the invalid input to prevent an infinite loop
                choice = 0; // Set choice to 0 to re-enter the loop
            }
        } while (choice != 8);

        scan.close();
    }
}