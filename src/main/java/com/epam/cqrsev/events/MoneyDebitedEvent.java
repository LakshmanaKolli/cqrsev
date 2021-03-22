package com.epam.cqrsev.events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDebitedEvent {
	
	private UUID id;
	
	private Integer amount;

}
