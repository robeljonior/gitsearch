package com.example.serchrepo.Service;

import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Repository.ProdactTypeRepository;
import com.example.serchrepo.Request.ProduactTypeRiquest;
import com.example.serchrepo.Response.ProduactTypeResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdactTypeService {


    private final ProdactTypeRepository repository;
    public ProduactTypeResponce createPT(ProduactTypeRiquest request) {

        LocalDateTime difoltTime = LocalDateTime.now();

        ProductType productType = repository.findByProductTypeName(request.getProductTypeName());

        if (productType == null){

            ProductType productType1 = new ProductType();

            productType1.setProductTypeName(request.getProductTypeName());
            productType1.setProductTypeDescription(request.getProductTypeDescription());
            productType1.setCreatedDate(difoltTime);

            return repository.save(productType1).produactTypeResponce();


        }
        else {
            return null;
        }
    }

    public ProduactTypeResponce update(Long id,ProduactTypeRiquest request) {
        ProductType productType = repository.findById(id).orElseThrow(()->new RuntimeException("this is not found"));

        if (productType != null){

            productType.setProductTypeName(request.getProductTypeName());
            productType.setProductTypeDescription(request.getProductTypeDescription());

        }
        else {
            return null;
        }
        return repository.save(productType).produactTypeResponce();
    }

    public List<ProduactTypeResponce> getAll() {
        return repository.findAll().stream()
                .map(ProductType::produactTypeResponce)
                .collect(Collectors.toList());

    }
}

