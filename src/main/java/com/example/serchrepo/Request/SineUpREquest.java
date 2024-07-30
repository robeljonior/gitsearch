package com.example.serchrepo.Request;

import com.example.serchrepo.Model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SineUpREquest {

    private Long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNo;
    private String password;
    private String token;
    private Role role;
    private String teamLeader;
    private String department;
    private boolean status;


}
