package com.epam.cqrsev.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankaccount")
public class BankAccount {
	@Id
	@Type(type = "uuid-char")
	private UUID id;
	
	private String owner;
	
	private Integer balance;
}
