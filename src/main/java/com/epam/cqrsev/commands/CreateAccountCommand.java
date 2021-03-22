package com.epam.cqrsev.commands;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountCommand {
	
	@TargetAggregateIdentifier
	private UUID id;
	
	private String owner;
	
	private Integer balance;

}
