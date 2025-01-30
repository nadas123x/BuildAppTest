package com.bank.kata.service.impl;

import com.bank.kata.bo.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
    }

    @Test
    void testDepositIncreasesBalanceAndAddsTransaction() {
        accountService.deposit(1000);

        assertEquals(1000, accountService.getBalance());
        List<Transaction> transactions = accountService.getTransactions();
        assertEquals(1, transactions.size());
        assertEquals(1000, transactions.get(0).getAmount());
        assertEquals(1000, transactions.get(0).getBalance());
        assertEquals(LocalDate.of(2012, 1, 10), transactions.get(0).getDate());
    }

    @Test
    void testWithdrawDecreasesBalanceAndAddsTransaction() {

        accountService.deposit(1000);
        accountService.withdraw(500);

        assertEquals(500, accountService.getBalance());
        List<Transaction> transactions = accountService.getTransactions();
        assertEquals(2, transactions.size());
        assertEquals(-500, transactions.get(1).getAmount());
        assertEquals(500, transactions.get(1).getBalance());
        assertEquals(LocalDate.of(2012, 1, 13), transactions.get(1).getDate());
    }

    @Test
    void testWithdrawThrowsExceptionIfInsufficientFunds() {
        accountService.deposit(500);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(1000));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void testDepositThrowsExceptionIfAmountIsNegative() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> accountService.deposit(-100));
        assertEquals("Deposit amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testWithdrawThrowsExceptionIfAmountIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(-100));
        assertEquals("Withdrawal amount must be greater than zero", exception.getMessage());
    }

    @Test
    void testPrintStatementPrintsTransactionsInReverseChronologicalOrder() {

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);
        accountService.printStatement();


    }
}
