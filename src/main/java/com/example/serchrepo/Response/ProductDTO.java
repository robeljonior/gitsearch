package com.example.serchrepo.Response;


import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Model.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private Bank bank;
    private ProductType productType;
    private String latestBranch;
    private String description;
    private String link;
    private String cicd;
    @NotNull
    private boolean readme;
    private LocalDateTime createDate;
    private String receivedFrom;
    private Long banks;


}
