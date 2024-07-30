package com.example.serchrepo.Controller;

import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Request.UserUpdateRiquest;
import com.example.serchrepo.Response.AuthenticationResponse;
import com.example.serchrepo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminControler {

    private final UserService userservise;


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Admin') or hasAnyRole('User')")
    public  Userr updateById(@PathVariable Long id, @RequestBody SineUpREquest rEquest){
        return userservise.updateById(id , rEquest);
    }

    @GetMapping("/Getalluser")
    @PreAuthorize("hasAnyRole('Admin') or hasAnyRole('User')")
    public List<SineUpREquest> getallusers(){
        return userservise.getAllUsers();
    }
    @GetMapping("/GetByemail")
    @PreAuthorize("hasAnyRole('Admin') or hasAnyRole('User')")
    public Userr getAllByEmail(String email){
        return userservise.getAllByUser(email);
    }
    @DeleteMapping("/suspendUser/{userId}")
    @PreAuthorize("hasAnyRole('Admin') or hasAnyRole('User')")
    public ResponseEntity<String> suspendUser(@PathVariable Long userId) {
        userservise.suspendUser(userId);
        return ResponseEntity.ok("User suspended successfully.");
    }

    @PutMapping("/suspendUser/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> ActivateUser(@PathVariable Long userId) {
        userservise.ActivateUser(userId);
        return ResponseEntity.ok("User activated successfully.");
    }
    @GetMapping("/vewuser")
    @PreAuthorize("hasAnyRole('Admin') or hasAnyRole('User')")
    public SineUpREquest vewuser(Long id){
        return userservise.vewuser(id);
    }

}