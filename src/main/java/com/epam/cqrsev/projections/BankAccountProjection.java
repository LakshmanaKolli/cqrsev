package com.epam.cqrsev.projections;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.events.AccountCreatedEvent;
import com.epam.cqrsev.events.MoneyCreditedEvent;
import com.epam.cqrsev.events.MoneyDebitedEvent;
import com.epam.cqrsev.exceptions.AccountNotFoundException;
import com.epam.cqrsev.queries.FindBankAccountQuery;
import com.epam.cqrsev.repository.BankAccountRepository;

@Component
public class BankAccountProjection {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@EventHandler
	public void on(AccountCreatedEvent event) {
		BankAccount bankAccount = new BankAccount(event.getId(), event.getOwner(), event.getBalance());
		bankAccountRepository.save(bankAccount);
	}
	
	@EventHandler
	public void on(MoneyCreditedEvent event) throws AccountNotFoundException {
		Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(event.getId());
		if(optionalBankAccount.isEmpty())
			throw new AccountNotFoundException(event.getId());
		else {
			BankAccount bankAccount = optionalBankAccount.get();
			bankAccount.setBalance(event.getAmount()+bankAccount.getBalance());
			bankAccountRepository.save(bankAccount);
		}
	}
	
	@EventHandler
	public void on(MoneyDebitedEvent event) throws AccountNotFoundException {
		Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(event.getId());
		if(optionalBankAccount.isEmpty())
			throw new AccountNotFoundException(event.getId());
		else {
			BankAccount bankAccount = optionalBankAccount.get();
			bankAccount.setBalance(bankAccount.getBalance()-event.getAmount());
			bankAccountRepository.save(bankAccount);
		}
	}
	
	@QueryHandler
	public BankAccount handle(FindBankAccountQuery query) {
		return bankAccountRepository.findById(query.getAccountId()).orElse(null); 
	}

}
