package com.zipcode.moneymakers.demo.controllers;

import com.zipcode.moneymakers.demo.models.Account;
import com.zipcode.moneymakers.demo.repositories.AccountRepository;
import com.zipcode.moneymakers.demo.service.AccountService;
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

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class AccountControllerTest {

    @MockBean
    @Autowired
    AccountService service;

    AccountController controller;

    AccountRepository repository;




    @Before
    public void init(){
   this.controller = new AccountController(service);


    }

//    @Test
//    public void findByStatusTest(){
//        HttpStatus expected = HttpStatus.OK;
//        Account account1 = new Account();
//        account1.setNickname("account1");
//
//        Account account2 = new Account();
//        account1.setNickname("account2");
//
//        List<Account> list = new ArrayList<>();
//        list.add(account1);
//        list.add(account2);
//      Mockito.when(repository.findByStatus(true)).thenReturn(list);
//        ResponseEntity<List<Account>> response = controller.findByStatus();
//        HttpStatus actual = response.getStatusCode();
//        List<Account> expectList =response.getBody();
//
//        Assert.assertEquals(expected,actual);
//        Assert.assertEquals(list,expectList);
//
//    }



    @Test
    public void getAccountByIdTest (){
        HttpStatus expected = HttpStatus.OK;
        Account account1 = new Account();
        account1.setId(1L);
        Mockito.when(service.findAccountById(Mockito.anyLong())).thenReturn(account1);

        ResponseEntity<Account> response = controller.getAccountById(1L);

        HttpStatus actual = response.getStatusCode();
        Account expectAccount =response.getBody();

        Assert.assertEquals(expected,actual);
        Assert.assertEquals(account1,expectAccount);






    }

    @Test
    public void  getAllAccountsTest(){
        HttpStatus expected = HttpStatus.OK;

        Account account1 = new Account();
        account1.setNickname("account1");

        Account account2 = new Account();
        account1.setNickname("account2");
        List<Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
       Mockito.when(service.findAllAccounts()).thenReturn(list);

        ResponseEntity<List<Account>> response = controller.getAllAccounts(null);

        HttpStatus actual = response.getStatusCode();
        List<Account> expectList =response.getBody();

        Assert.assertEquals(expected,actual);
        Assert.assertEquals(list,expectList);

        response = controller.getAllAccounts("account1");
       actual = response.getStatusCode();
        expectList =response.getBody();
         list.clear();

//        Assert.assertEquals(expected,actual);
//        Assert.assertEquals(list,expectList);
//        expected = HttpStatus.INTERNAL_SERVER_ERROR;
//
//        response = controller.getAllAccounts("1234");
//        actual = response.getStatusCode();
//        expectList =response.getBody();
//        list.clear();
//
//        Assert.assertEquals(expected,actual);

    }

        @Test
    public void createTest() {
        HttpStatus expected = HttpStatus.CREATED;
        Long id = 1L;
        Account expectedAccount = new Account();
        expectedAccount.setNickname("Test");
        expectedAccount.setBalance(100.0);
        expectedAccount.setId(id);

        Mockito.when(service.createAccount(expectedAccount)).thenReturn(expectedAccount);
        ResponseEntity<Account> response = controller.createAccount(expectedAccount);
        HttpStatus actual = response.getStatusCode();

        Assert.assertEquals(expected, actual);


    }
    @Test
    public void updateAccountTest() {
        HttpStatus expected = HttpStatus.OK;
        Long id = 1L;
        Account expectedAccount = new Account();
        expectedAccount.setBalance(100.0);
        expectedAccount.setId(id);

        Mockito.when(service.updateAccount(id, expectedAccount)).thenReturn(expectedAccount);
        ResponseEntity<Account> response = controller.updateAccount(id, expectedAccount);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        Assert.assertEquals(expected, actual);
        assertEquals(expectedAccount, actualAccount);


    }


    @Test
    public  void deleteAllAccountsTest(){
        HttpStatus expected = HttpStatus.NO_CONTENT;

        ResponseEntity<List<Account>> response = controller.deleteAllAccounts();

        HttpStatus actual = response.getStatusCode();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void deleteAccountById(){
        HttpStatus expected = HttpStatus.OK;
        Long id = 1L;
        Account account = new Account();
        account.setBalance(100.0);
        account.setId(id);
        Boolean expectedDeleted = true;

        ResponseEntity<Account> response = controller.deleteAccount(id);
        HttpStatus actual = response.getStatusCode();

        Assert.assertEquals(expected, actual);

    }




}