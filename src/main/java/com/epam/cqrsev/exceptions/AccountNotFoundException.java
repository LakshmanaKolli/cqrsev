package com.epam.cqrsev.exceptions;

import java.util.UUID;

public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(UUID id) {
		super(String.format("Account not found for given account id : %s", id));
	}
}
