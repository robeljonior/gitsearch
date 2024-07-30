package com.example.serchrepo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankResponce {
    private Long id;
    private String BankName;
    private String BankDiscription;

}
