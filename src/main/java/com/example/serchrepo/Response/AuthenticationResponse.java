package com.example.serchrepo.Response;

import com.example.serchrepo.Model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Role role;
    private String refreshToken;
    private String accessToken;
    private String token;
    private String status;
    private String message;
}
