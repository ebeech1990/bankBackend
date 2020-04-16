package com.zipcode.moneymakers.demo.service;


import com.zipcode.moneymakers.demo.models.Account;
import com.zipcode.moneymakers.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//!!!!!!!!!!!

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAllAccountsThatContain(String nickname) {
        return new ArrayList<>(accountRepository.findByNicknameContaining(nickname));
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).get();
    }


    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }


    public Account updateAccount(Long id ,Account account) {
        Account retrievedAccount = findAccountById(id);
        retrievedAccount.setNickname(account.getNickname());
        retrievedAccount.setBalance(account.getBalance());
        retrievedAccount.setStatus(account.getStatus());
        retrievedAccount.setTransactions(account.getTransactions());
        return accountRepository.save(retrievedAccount);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

    public void deleteAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        for(Account a : accounts){
            accountRepository.delete(a);
        }
    }


}