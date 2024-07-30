package com.example.serchrepo.Request;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRiquest {
    private Long bank;
    private Long productType;
    private String latestBranch;
    private String description;
    private String link;
    private String cicd;
    private boolean readme;
    private String receivedFrom;
    private String repositoryName;
    private String UserRepoToken;

}

