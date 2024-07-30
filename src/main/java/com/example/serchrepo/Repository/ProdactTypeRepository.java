package com.example.serchrepo.Repository;

import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Request.ProductRiquest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdactTypeRepository extends JpaRepository<ProductType,Long> {


    ProductType findByProductTypeName(String productTypeName);
}
