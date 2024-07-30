package com.example.serchrepo.Repository;

import com.example.serchrepo.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {


    Bank findByBankName(String bankName);
}
