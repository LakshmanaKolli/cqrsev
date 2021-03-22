package com.epam.cqrsev.services;

import java.util.concurrent.CompletableFuture;

import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.dto.BankAccountDTO;
import com.epam.cqrsev.dto.MoneyInputDTO;

public interface AccountCommandService {
	
	CompletableFuture<BankAccount> createBankAccount(BankAccountDTO bankAccountDTO);

	CompletableFuture<String> creditBankAccount(String accountId, MoneyInputDTO moneyDTO);

	CompletableFuture<String> debitBankAccount(String accountId, MoneyInputDTO moneyDTO);

}
