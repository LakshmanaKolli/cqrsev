package com.epam.cqrsev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.cqrsev.domain.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID>{

}
