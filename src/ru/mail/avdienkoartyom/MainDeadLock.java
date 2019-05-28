package ru.mail.avdienkoartyom;

public class MainDeadLock {

    public static void main(String[] args) {

        MainDeadLock mainDeadLock = new MainDeadLock();
        Account account1 = mainDeadLock.new Account(1000);
        Account account2 = mainDeadLock.new Account(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                transfer(account1, account2, 500);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                transfer(account2, account1, 100);
            }
        }).start();


    }

    public static void transfer(Account account1, Account account2, int amount) {

        if (account1.getBalance() < amount) {
            System.out.println("Failure, insufficient funds");
        } else {
            synchronized (account1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (account2) {
                    account1.withdraw(amount);
                    account2.deposit(amount);
                }
            }
        }
    }

    public class Account {
        private int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            balance = balance - amount;
        }

        public void deposit(int amount) {
            balance = balance + amount;
        }
    }

}
