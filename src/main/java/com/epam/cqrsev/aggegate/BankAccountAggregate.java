package com.epam.cqrsev.aggegate;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.epam.cqrsev.commands.CreateAccountCommand;
import com.epam.cqrsev.commands.CreditMoneyCommand;
import com.epam.cqrsev.commands.DebitMoneyCommand;
import com.epam.cqrsev.events.AccountCreatedEvent;
import com.epam.cqrsev.events.MoneyCreditedEvent;
import com.epam.cqrsev.events.MoneyDebitedEvent;
import com.epam.cqrsev.exceptions.InsufficientBalanceException;

import lombok.Data;

@Data
@Aggregate
public class BankAccountAggregate {

	@AggregateIdentifier
	private UUID accountId;

	private String owner;

	private Integer balance;

	@CommandHandler
	public void handle(CreateAccountCommand command) {
		AggregateLifecycle.apply(new AccountCreatedEvent(command.getId(), command.getOwner(), command.getBalance()));
	}

	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {
		this.accountId = event.getId();
		this.balance = event.getBalance();
		this.owner = event.getOwner();
	}

	@CommandHandler
	public void handle(CreditMoneyCommand command) {
		AggregateLifecycle.apply(new MoneyCreditedEvent(command.getId(), command.getAmount()));
	}

	@EventSourcingHandler
	public void on(MoneyCreditedEvent event) {
		this.balance += event.getAmount();
	}

	@CommandHandler
	public void handle(DebitMoneyCommand command) {
		AggregateLifecycle.apply(new MoneyDebitedEvent(command.getId(), command.getAmount()));
	}

	@EventSourcingHandler
	public void on(MoneyDebitedEvent event) throws InsufficientBalanceException{
		if(this.balance < event.getAmount() )
			throw new InsufficientBalanceException(event.getId(), event.getAmount());
		this.balance -= event.getAmount();
	}
}
