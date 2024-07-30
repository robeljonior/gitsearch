package com.example.serchrepo.Service;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Repository.BankRepository;
import com.example.serchrepo.Request.BankRequest;
import com.example.serchrepo.Response.BankResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository repository;

    public BankResponce createBank(BankRequest request) {

        Bank bank = repository.findByBankName(request.getBankName());

        if (bank == null){
            Bank bank1 = new Bank();
            bank1.setBankName(request.getBankName());
            bank1.setBankDiscription(request.getBankDiscription());

            return repository.save(bank1).bankResponce();
        }
        else {
            return null;
        }

    }

    public BankResponce updateBank(BankRequest request) {
        Bank bank = repository.findByBankName(request.getBankName());

        if (bank != null){

            bank.setBankName(request.getBankName());
            bank.setBankDiscription(request.getBankDiscription());

            return repository.save(bank).bankResponce();
        }
        else {
            return null;
        }
    }

    public List<BankResponce> getall() {

        return repository.findAll().stream().map(Bank::bankResponce).collect(Collectors.toList());
    }
}
