package com.zipcode.moneymakers.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountTransaction{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long transactionId;
    private AccountTransactionType type;
    private double amount;
    private Date date;
    private Double transactionBalance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    protected AccountTransaction() {}

    public AccountTransaction(AccountTransactionType type, double amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
        this.transactionBalance = 0.0;
        this.account = account;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public AccountTransactionType getType() {
        return type;
    }

    public void setType(AccountTransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTransactionBalance() {
        return transactionBalance;
    }

    public void setTransactionBalance(Double transactionBalance) {
        this.transactionBalance = transactionBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                ", transactionBalance=" + transactionBalance +
                '}';
    }
}
