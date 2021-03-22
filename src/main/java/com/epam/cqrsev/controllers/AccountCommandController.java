package com.epam.cqrsev.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.dto.BankAccountDTO;
import com.epam.cqrsev.dto.MoneyInputDTO;
import com.epam.cqrsev.services.AccountCommandService;

@RestController
@RequestMapping("/accounts")
public class AccountCommandController {

	@Autowired
	private AccountCommandService accountCommandService;

	@PostMapping
	public CompletableFuture<BankAccount> createBankAccount(
			@RequestBody BankAccountDTO bankAccountDTO) {
		return accountCommandService.createBankAccount(bankAccountDTO);
	}

	@PutMapping(value = "/credit/{accountId}")
	public CompletableFuture<String> creditBankAccount(@PathVariable String accountId,
			@RequestBody MoneyInputDTO moneyDTO) {
		return accountCommandService.creditBankAccount(accountId, moneyDTO);
	}
	
	@PutMapping(value = "/debit/{accountId}")
	public CompletableFuture<String> debitBankAccount(@PathVariable String accountId,
			@RequestBody MoneyInputDTO moneyDTO) {
		return accountCommandService.debitBankAccount(accountId, moneyDTO);
	}

}
