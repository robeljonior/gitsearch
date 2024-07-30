package com.example.serchrepo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BankRequest {

    private Long id;
    private String BankName;
    private String BankDiscription;


}
