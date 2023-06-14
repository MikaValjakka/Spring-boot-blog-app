package com.example.Springbootblogapp.services;

import com.example.Springbootblogapp.models.Account;
import com.example.Springbootblogapp.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }

}
