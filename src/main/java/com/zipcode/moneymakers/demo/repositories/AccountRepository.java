package com.zipcode.moneymakers.demo.repositories;



import com.zipcode.moneymakers.demo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByStatus(Boolean status);
    List<Account> findByNicknameContaining(String nickname);
}


