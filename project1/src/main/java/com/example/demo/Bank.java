package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Bank {
    private List<Account> accounts;

    public Bank () {
        accounts = new ArrayList<Account> ();
    }
    public int find_account (String account_num) {
        int i;

        for (i = 0; i < accounts.size (); i++) {
            if (accounts.get (i).get_account_num ().equals (account_num)) {
                return i;
            }
        }

        return -1;
    }
    public String deposit (int amount, String account_num) {
        int index = find_account (account_num);

        if (index >= 0) {
            accounts.get (index).deposit (amount);
        }
        else {
            accounts.add(new Account (amount, account_num));
        }

        return "0 Deposit successful";
    }

    public String withdraw (int amount, String account_num) {
        int index = find_account (account_num);

        if (index >= 0) {
            int status = accounts.get (index).withdraw (amount);

            if (status == 0) {
                return "0 Withdraw successful";
            }
            else {
                return "1 Insufficient funds";
            }
        }
        else {
            return "2 Unknown account number";
        }
    }

    public String show_balance (String account_num) {
        int index = find_account (account_num);

        if (index >= 0) {
            int balance = accounts.get (index).get_balance ();
            return "0 Balance: " + Integer.toString (balance);
        }
        else {
            return "2 Unknown account number";
        }
    }
}
