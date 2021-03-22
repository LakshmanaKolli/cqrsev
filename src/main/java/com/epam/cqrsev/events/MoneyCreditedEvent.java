package com.epam.cqrsev.events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyCreditedEvent {
	
	private UUID id;
	
	private Integer amount;

}
