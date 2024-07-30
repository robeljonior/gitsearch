package com.example.serchrepo.Service;

import com.example.serchrepo.Config.JwtService;
import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Request.AuthenticationRequest;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserrRepo userrRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    public AuthenticationResponse register(SineUpREquest request) {
        LocalDateTime defoltTime = LocalDateTime.now();

        if (userrRepo.existsUserrByEmail(request.getEmail())){
//            Username is already in use
            return AuthenticationResponse.builder()
                    .status("error")
                    .message("Username is already in use. Please choose a different username")
                    .build();
        }
        if (userrRepo.existsUserrByUserName(request.getUserName()))
        {
            return AuthenticationResponse.builder()
                    .status("error")
                    .message("Username is already in use. Please choose a different username")
                    .build();
        }


        var user = Userr.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .phoneNo(request.getPhoneNo())
                .password(request.getTeamLeader())
                .department(request.getDepartment())
                .registrationDate(defoltTime)
                .gitToken(request.getToken())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .status(true)
                .build();

        userrRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generaterefreshtoken(new HashMap<>(), user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .status("success")
                .message("Registration Successfully!")
                .build();



    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
        ))

        ;
        Userr byUserName = userrRepo.findByUserName(request.getUserName());
        String token = jwtService.generateToken(byUserName);
        System.out.println("Generated token" + token);
        var refreshToken = jwtService.generaterefreshtoken(new HashMap<>(), byUserName);


        if (byUserName.isStatus()) {
            return AuthenticationResponse.builder()
                    .token(token)
                    .role(byUserName.getRole())
                    .refreshToken(refreshToken)
                    .status("sucsess")
                    .message("user rigisterd secsossfully")
                    .build();
        }
        return new AuthenticationResponse(null, "stop it you are just denied", "please contact your admin", "fuck you", "false", "you are denied");

    }
    }

