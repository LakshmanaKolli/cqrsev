package com.epam.cqrsev.exceptions;

import java.util.UUID;

public class InsufficientBalanceException extends Exception {
	
	public InsufficientBalanceException(UUID id, Integer balance) {
		super(String.format("Insufficient balace : can't debit %s from account %s",id,balance));
	}

}
