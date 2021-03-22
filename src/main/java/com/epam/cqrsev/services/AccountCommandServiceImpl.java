package com.epam.cqrsev.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.cqrsev.commands.CreateAccountCommand;
import com.epam.cqrsev.commands.CreditMoneyCommand;
import com.epam.cqrsev.commands.DebitMoneyCommand;
import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.dto.BankAccountDTO;
import com.epam.cqrsev.dto.MoneyInputDTO;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

	@Autowired
	private CommandGateway commandGateway;

	@Override
	public CompletableFuture<BankAccount> createBankAccount(BankAccountDTO bankAccountDTO) {
		return commandGateway.send(
				new CreateAccountCommand(UUID.randomUUID(), bankAccountDTO.getOwner(), bankAccountDTO.getBalance()));
	}

	@Override
	public CompletableFuture<String> creditBankAccount(String accountId, MoneyInputDTO moneyDTO) {
		return commandGateway.send(new CreditMoneyCommand(UUID.fromString(accountId), moneyDTO.getAmount()));
	}

	@Override
	public CompletableFuture<String> debitBankAccount(String accountId, MoneyInputDTO moneyDTO) {
		return commandGateway.send(new DebitMoneyCommand(UUID.fromString(accountId), moneyDTO.getAmount()));
	}


}
