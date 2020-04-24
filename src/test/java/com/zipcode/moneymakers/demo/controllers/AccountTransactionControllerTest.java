package com.zipcode.moneymakers.demo.controllers;

import com.zipcode.moneymakers.demo.models.Account;
import com.zipcode.moneymakers.demo.models.AccountTransaction;
import com.zipcode.moneymakers.demo.service.AccountService;
import com.zipcode.moneymakers.demo.service.AccountTransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class AccountTransactionControllerTest {
    AccountTransactionController controller;
    @MockBean
    @Autowired
    AccountTransactionService accountTransactionService;

    @MockBean
    @Autowired
    AccountService accountService;
    @Before
    public void init(){
        this.controller= new AccountTransactionController(accountTransactionService,accountService);
    }




    @Test
   public void getAllTransactions() {
        HttpStatus expected = HttpStatus.OK;

        AccountTransaction saving = new AccountTransaction();
        saving.setAmount(100.0);

        AccountTransaction checking = new AccountTransaction();
        checking.setAmount(20.0);
        checking.setTransactionId(1L);
        List<AccountTransaction> userAccount = new ArrayList<>();
        userAccount.add(saving);
        userAccount.add(checking);

        Mockito.when(accountTransactionService.findAllTransactions(Mockito.anyLong())).thenReturn(userAccount);
        ResponseEntity<List<AccountTransaction>> response = controller.getAllTransactions(1L);
        HttpStatus actual = response.getStatusCode();
        List<AccountTransaction> accountTransactionList = response.getBody();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(userAccount, accountTransactionList);




    }

    @Test
  public   void putDepositToAccountTest() {

        HttpStatus expected = HttpStatus.OK;
        AccountTransaction transaction = new AccountTransaction();
        Long id = 1L;
        Double depositAmount = 10.0;
        Double expectedAmount = 110.0;
        transaction.setTransactionBalance(depositAmount);

        transaction.setAmount(expectedAmount);
        Account account = new Account();
        account.setId(id);
        account.setBalance(110.0);

        Mockito.when(accountTransactionService.depositToAccount(Mockito.anyLong(),Mockito.any(),Mockito.any()))
                .thenReturn(transaction);
        ResponseEntity<AccountTransaction> response = controller.putDepositToAccount(id,depositAmount,account);
        HttpStatus actual = response.getStatusCode();
        AccountTransaction actualAmount = response.getBody();

        Assert.assertEquals(expected, actual);
        assertEquals(expectedAmount, actualAmount.getAmount());



    }

    @Test
  public   void withdrawTest() {
        HttpStatus expected = HttpStatus.OK;
        AccountTransaction transaction = new AccountTransaction();
        Long id = 1L;
        Double depositAmount = 10.0;
        Double expectedAmount = 110.0;
        transaction.setTransactionBalance(depositAmount);

        transaction.setAmount(expectedAmount);
        Account account = new Account();
        account.setId(id);
        account.setBalance(110.0);

        Mockito.when(accountTransactionService.withdrawFromAccount(Mockito.anyLong(),Mockito.any(),Mockito.any()))
                .thenReturn(transaction);
        ResponseEntity<AccountTransaction> response = controller.withdraw(id,depositAmount,account);
        HttpStatus actual = response.getStatusCode();
        AccountTransaction actualAmount = response.getBody();

        Assert.assertEquals(expected, actual);
        assertEquals(expectedAmount, actualAmount.getAmount());
    }

    @Test
   public void transferTest() {
        HttpStatus expected = HttpStatus.OK;
   Account  account1 = new Account();
   Account account2 = new Account();
   List<Account> list=new ArrayList<>();
   list.add(account1);
   list.add(account2);
        Mockito.when(accountService.findAllAccounts()).thenReturn(list);
        ResponseEntity<List<Account>> response = controller.transfer();
        HttpStatus actual = response.getStatusCode();

        List<Account> accountList = response.getBody();
        Assert.assertEquals(expected, actual);
        assertEquals(list,accountList);
    }
}