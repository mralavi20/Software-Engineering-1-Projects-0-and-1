package com.example.demo;

public class Account {
    private String account_num;
    private int balance;

    public Account (int input_balance, String input_account_num) {
        balance = input_balance;
        account_num = input_account_num;
    }

    public int deposit (int amount) {
        balance = balance + amount;
        return 0;
    }

    public int withdraw (int amount) {
        if (amount <= balance) {
            balance = balance - amount;
            return 0;
        }
        else {
            return 1;
        }
    }

    public int get_balance () {
        return balance;
    }

    public String get_account_num () {
        return account_num;
    }
}