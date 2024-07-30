package com.example.serchrepo.Request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRiquest {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String userName;
    private String teamLeader;
    private String department;
}
