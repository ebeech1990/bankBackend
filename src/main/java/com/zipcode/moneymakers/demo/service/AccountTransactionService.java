package com.zipcode.moneymakers.demo.service;


import com.zipcode.moneymakers.demo.models.Account;
import com.zipcode.moneymakers.demo.models.AccountTransaction;
import com.zipcode.moneymakers.demo.models.AccountTransactionType;
import com.zipcode.moneymakers.demo.repositories.AccountRepository;
import com.zipcode.moneymakers.demo.repositories.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountTransaction> findAllTransactions(Long id){
        Account account = accountService.findAccountById(id);
        return new ArrayList<>(account.getTransactions());
    }



    public AccountTransaction depositToAccount(Long accountId, Account account, Double depositAmount){
        Account retrievedAccount = accountService.findAccountById(accountId);
        AccountTransaction accountTransaction = new AccountTransaction(AccountTransactionType.DEPOSIT, depositAmount, retrievedAccount);
        accountTransaction.setTransactionBalance(depositAmount);
        retrievedAccount.addTransaction(accountTransaction);
        Double newBalance = retrievedAccount.getBalance() + accountTransaction.getTransactionBalance();
        retrievedAccount.setBalance(newBalance);
        accountRepository.save(retrievedAccount);
        System.out.println(retrievedAccount.getBalance());
        return accountTransaction;

    }

    public AccountTransaction withdrawFromAccount(Long accountId ,Account account, Double withdrawAmount){
        Account retrievedAccount = accountService.findAccountById(accountId);
        AccountTransaction accountTransaction = new AccountTransaction(AccountTransactionType.WITHDRAWAL, withdrawAmount, retrievedAccount);
        accountTransaction.setTransactionBalance(withdrawAmount);
        retrievedAccount.addTransaction(accountTransaction);
        Double newBalance = retrievedAccount.getBalance() - accountTransaction.getTransactionBalance();
        retrievedAccount.setBalance(newBalance);
        accountRepository.save(retrievedAccount);
        return accountTransaction;

    }


    public List<AccountTransaction> getTransactionsByType(AccountTransactionType type){
        return accountTransactionRepository.findByType(type);
    }

    public String getSummaryById(Long accountTransactionId){
        AccountTransaction accountTransaction = accountTransactionRepository.findById(accountTransactionId).get();
        return accountTransaction.toString();
    }


}
