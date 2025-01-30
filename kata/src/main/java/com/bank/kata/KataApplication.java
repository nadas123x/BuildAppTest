package com.bank.kata;

import com.bank.kata.service.impl.AccountServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KataApplication {

	public static void main(String[] args) {
		AccountServiceImpl accountService = new AccountServiceImpl();

		System.out.println("== Dépôt de 1000 le 10-01-2012 ==");
		accountService.deposit(1000);

		System.out.println("== Dépôt de 2000 le 13-01-2012 ==");
		accountService.deposit(2000);

		System.out.println("== Retrait de 500 le 14-01-2012 ==");
		accountService.withdraw(500);

		System.out.println("\n== Historique des transactions ==");
		accountService.printStatement();
	}

}
