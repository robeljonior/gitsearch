package com.example.serchrepo.Controller;

import com.example.serchrepo.Model.Products;
import com.example.serchrepo.Request.ProductRiquest;
import com.example.serchrepo.Response.ProductDTO;
import com.example.serchrepo.Service.ProdactService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cteating/link")
public class ProductController {

    private final ProdactService service;

//
//    @PostMapping("/create/products")
//    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
//
//    public ProductDTO rigisterProduct(@RequestBody ProductRiquest request){
//        return service.createProduct(request);
//    }
    @PostMapping("/create/products")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    public ProductDTO rigisterProduct(@RequestBody ProductRiquest request) throws IOException, URISyntaxException, InterruptedException {
        return service.createProduct(request);
    }

    @PutMapping("Update/{id}")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    public Products udateProduct(@PathVariable Long id , @RequestBody ProductRiquest request ){
        return service.update(id, request);
    }


    @GetMapping("/Getall")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")

    public List<ProductDTO> Getall(){
        return service.getAlls();
    }
    @GetMapping("/SearchPeodacts")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    public List<ProductDTO> SearcAll(@RequestParam("query") String query){
        return service.searchProduct(query);
    }
    @GetMapping("/vewprodacts")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    public ProductDTO Vewproduct(@RequestParam Long id){
        return service.vewprodacts(id);
    }

}
