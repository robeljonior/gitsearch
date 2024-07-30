package com.example.serchrepo.Repository;

import com.example.serchrepo.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
//    Object findByLink(String link);

    Products findByLink(String link);



    @Query("select e from Products e where ((e.productType.productTypeName) LIKE lower(concat('%',:query,'%')))" +
            "or ((e.bank.bankName) like lower(concat('%', :query,'%') ) )" +
            "or ((e.latestBranch) like lower(concat('%', :query,'%') ) )" +
            "or((e.description) like lower(concat('%',:query,'%') ) )" +
            "or((e.link) like lower(concat('%',:query,'%') ) )" +
            "or ((e.cicd) like lower(concat('%',:query,'%') ) )" +
            "or ((e.receivedFrom) like lower(concat('%',:query,'%') ) )"
    )
    List<Products> searchQuery(String query);
}
