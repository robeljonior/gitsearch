package com.example.serchrepo.Model;

import com.example.serchrepo.Response.BankResponce;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankName;
    private String bankDiscription;

    public BankResponce bankResponce(){
        BankResponce bank = new BankResponce();
        bank.setId(id);
        bank.setBankName(bankName);
        bank.setBankDiscription(bankDiscription);
        return bank;
    }

}
