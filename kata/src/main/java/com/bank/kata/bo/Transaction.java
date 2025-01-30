package com.bank.kata.bo;

import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final int balance;

    public Transaction(LocalDate date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("%s || %d || %d", date, amount, balance);
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }


}
