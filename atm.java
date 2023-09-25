import java.util.*;

class bankacc {
    private double balance;

    public bankacc(double currentbal) {

        balance = currentbal;
    }

    public double getbalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= 1000)
            System.out.println("Invalid request! Amoount should not below 1000/-");
        else if (amount >= balance) {
            System.out.println("Invalid request!");
        } else {
            balance = balance - amount;
            System.out.println("Amount successfully withdrawn! Current balance: " + balance);
        }
    }

    public void deposit(double amount) {
        if (amount <= 0)
            System.out.println("Please enter valid amount");
        else {
            balance = balance + amount;
            System.out.println("Amount deposited succesfully! Current balance: " + balance);
        }
    }
}

public class atm {
    private static bankacc useracc;

    public atm(bankacc acc) {
        useracc = acc;
    }

    public static void withdraw(double amount) {
        useracc.withdraw(amount);
    }

    public static void deposit(double amount) {
        useracc.deposit(amount);
    }

    public static void checkbalance() {
        System.out.println("Current balance:" + useracc.getbalance());
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enetr current balance of bank account");
        double currentbal = sc.nextDouble();
        bankacc useracc = new bankacc(currentbal);
        atm ob = new atm(useracc);
        while (true) {
            System.out.println();
            System.out.println("Press 1. for Withdrawl");
            System.out.println("Press 2. for Deposit");
            System.out.println("Press 3. for Balance enquiry");
            System.out.println("Press 4. EXIT");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter amount to be withdrawn");
                    double amt1 = sc.nextDouble();
                    atm.withdraw(amt1);
                    break;
                case 2:
                    System.out.println("Enter amount to deposit");
                    double amt2 = sc.nextDouble();
                    atm.deposit(amt2);
                    break;
                case 3:
                    atm.checkbalance();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Inavlid choice! Please select a valid input");
            }
        }
    }
}