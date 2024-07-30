package com.example.serchrepo.Model;

import com.example.serchrepo.Request.SineUpREquest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Userr implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(name = "user_name",updatable = false,unique = true)
    private String userName;
    private String email;
    private String phoneNo;
    private String password;
    private String teamLeader;
    private String department;
    private String gitToken;
    @Enumerated
    private Role role;

    private boolean status;



    @Column(name = "registration_date")
    private LocalDateTime registrationDate;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SineUpREquest sineUpREquest(){
        SineUpREquest sinup = new SineUpREquest();
        sinup.setId(id);
        sinup.setFirstName(firstName);
        sinup.setLastName(lastName);
        sinup.setUserName(userName);
        sinup.setEmail(email);
        sinup.setPhoneNo(phoneNo);
        sinup.setToken(gitToken);
        sinup.setTeamLeader(teamLeader);
        sinup.setDepartment(department);
        sinup.setRole(role);
        sinup.setStatus(status);

        return sinup;
    }


}
