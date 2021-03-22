package com.epam.cqrsev.services;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.cqrsev.domain.BankAccount;
import com.epam.cqrsev.queries.FindBankAccountQuery;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

	@Autowired
	private QueryGateway queryGateway;

	@Autowired
	private EventStore eventStore;

	@Override
	public CompletableFuture<BankAccount> findById(String accountId) {
		return queryGateway.query(new FindBankAccountQuery(UUID.fromString(accountId)),
				ResponseTypes.instanceOf(BankAccount.class));
	}

	@Override
	public List<Object> listEventsForAccount(String accountId) {
		return eventStore.readEvents(UUID.fromString(accountId).toString()).asStream().map(Message::getPayload)
				.collect(Collectors.toList());
	}

}
