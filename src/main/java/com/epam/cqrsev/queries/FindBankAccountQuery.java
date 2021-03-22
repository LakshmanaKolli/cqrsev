package com.epam.cqrsev.queries;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindBankAccountQuery {
	
	private UUID accountId;

}
