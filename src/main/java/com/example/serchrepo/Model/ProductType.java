package com.example.serchrepo.Model;

import com.example.serchrepo.Response.ProduactTypeResponce;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    private String productTypeName;
//    private String productTypeDescription;
//    private Date createdDate;
    private String productTypeName;
    private String productTypeDescription;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }


    public ProduactTypeResponce produactTypeResponce() {


        ProduactTypeResponce respo = new ProduactTypeResponce();
        respo.setId(id);
        respo.setProductTypeName(productTypeName);
        respo.setProductTypeDescription(productTypeDescription);
        respo.setCreatedDate(createdDate);
        return respo;
    }


}
