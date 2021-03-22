package com.epam.cqrsev.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.epam.cqrsev.domain.BankAccount;

public interface AccountQueryService {

	CompletableFuture<BankAccount> findById(String accountId);

	List<Object> listEventsForAccount(String accountId);

}
