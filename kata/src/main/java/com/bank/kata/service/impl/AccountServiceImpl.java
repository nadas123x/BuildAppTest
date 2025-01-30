package com.bank.kata.service.impl;

import com.bank.kata.bo.Transaction;
import com.bank.kata.service.AccountService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<LocalDate> simulatedDates = List.of(
            LocalDate.of(2012, 1, 10),
            LocalDate.of(2012, 1, 13),
            LocalDate.of(2012, 1, 14)
    );
    private int dateIndex = 0;

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        balance += amount;
        transactions.add(new Transaction(getNextDate(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(getNextDate(), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        transactions.stream()
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .forEach(System.out::println);
    }

    private LocalDate getNextDate() {
        if (dateIndex >= simulatedDates.size()) {
            throw new IllegalStateException("No more dates available for simulation");
        }
        return simulatedDates.get(dateIndex++);
    }
    public int getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

}
