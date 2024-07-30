package com.example.serchrepo.Controller;

import com.example.serchrepo.Request.ProduactTypeRiquest;
import com.example.serchrepo.Response.ProduactTypeResponce;
import com.example.serchrepo.Service.ProdactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ProdactType")
public class ProductTypeController {
    private final ProdactTypeService service;



    @PostMapping("/createProdactType")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")

    public ProduactTypeResponce createPT(@RequestBody ProduactTypeRiquest request){
        return service.createPT(request);
    }
    @PutMapping("/UpdateProduct")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    ProduactTypeResponce update(@PathVariable Long id, @RequestBody ProduactTypeRiquest request){

        return service.update(id,request);

    }
    @GetMapping("/Getallproductname")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    List<ProduactTypeResponce> getAll(){
        return service.getAll();
    }



}
