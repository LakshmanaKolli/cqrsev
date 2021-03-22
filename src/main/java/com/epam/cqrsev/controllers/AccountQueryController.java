package com.epam.cqrsev.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.services.AccountQueryService;

@RestController
@RequestMapping("/accounts")
public class AccountQueryController {

	@Autowired
	private AccountQueryService accountQueryService;

	@GetMapping("/{accountId}")
	public CompletableFuture<BankAccount> findById(@PathVariable("accountId") String accountId) {
		return accountQueryService.findById(accountId);
	}

	@GetMapping("/{accountId}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountId") String accountId) {
		return accountQueryService.listEventsForAccount(accountId);
	}

}
