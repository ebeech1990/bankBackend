package com.zipcode.moneymakers.demo.repositories;


import com.zipcode.moneymakers.demo.models.AccountTransaction;
import com.zipcode.moneymakers.demo.models.AccountTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByType(AccountTransactionType type);
}
