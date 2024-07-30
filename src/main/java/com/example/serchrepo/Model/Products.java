package com.example.serchrepo.Model;

import com.example.serchrepo.Response.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bank bank;
    @ManyToOne
    private ProductType productType;


    private String latestBranch;
    private String description;
//    @URL(message = "Invalid URL format")
    private String link;
    private String cicd;
    private boolean readme;
    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    private String receivedFrom;
    private String repositoryName;

    private String UserRepoToken;



    public ProductDTO productDTO(){
        ProductDTO productDTO =new ProductDTO();
        productDTO.setId(id);
        productDTO.setBank(bank);
        productDTO.setLatestBranch(latestBranch);
        productDTO.setProductType(productType);
        productDTO.setDescription(description);
        productDTO.setLink(link);
        productDTO.setCicd(cicd);
        productDTO.setReadme(readme);
        productDTO.setCreateDate(receivedDate);
        productDTO.setReceivedFrom(receivedFrom);

        return productDTO;
    }

}
