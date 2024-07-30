package com.example.serchrepo.Controller;

import com.example.serchrepo.Request.BankRequest;
import com.example.serchrepo.Response.BankResponce;
import com.example.serchrepo.Service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService service;


    @PostMapping("/CreateBnak")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")
    public BankResponce createBank(@RequestBody BankRequest request){
        return service.createBank(request);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")

    public BankResponce updateBank(@RequestBody BankRequest request){
        return service.updateBank(request);
    }
    @GetMapping("/GetAllBnkimg")
    @PreAuthorize("hasAnyRole('User') or hasAnyRole('Admin')")

    public List<BankResponce> getall(){
        return service.getall();
    }


}
