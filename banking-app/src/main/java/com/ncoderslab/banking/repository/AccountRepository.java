package com.ncoderslab.banking.repository;

import com.ncoderslab.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
