package com.ncoderslab.banking.service.impl;

import com.ncoderslab.banking.dto.AccountDto;
import com.ncoderslab.banking.dto.mapper.AccountMapper;
import com.ncoderslab.banking.entity.Account;
import com.ncoderslab.banking.repository.AccountRepository;
import com.ncoderslab.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account newAccount = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = this.accountRepository.save(newAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(user);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double updatedBalance = user.getBalance() + amount;
        user.setBalance(updatedBalance);
        accountRepository.save(user);
        return AccountMapper.mapToAccountDto(user);
    }

    @Override
    public AccountDto withdraw(long id, double withdrawAmount) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (withdrawAmount > user.getBalance()) {
            throw new RuntimeException("Insufficient balance");
        }
        double updatedBalance = user.getBalance() - withdrawAmount;
        user.setBalance(updatedBalance);
        accountRepository.save(user);
        return AccountMapper.mapToAccountDto(user);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }
}
