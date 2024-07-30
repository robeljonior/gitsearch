package com.example.serchrepo.Service;

import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Request.UserUpdateRiquest;
import com.example.serchrepo.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserrRepo repository;


    public UserDetailsService userdetalService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }




    public void delateuser(Long id) {
        repository.deleteById(id);
    }

    public List<SineUpREquest> getAllUsers() {
        return repository.findAll().stream()
                .map(Userr::sineUpREquest).collect(Collectors.toList());
    }


    public Userr getAllByUser(String email) {
        return repository.findByEmail(email).orElseThrow(()->new RuntimeException("this email not fond"));

    }
    public void suspendUser(Long id){
        Userr userr = repository.findById(id).orElseThrow(()->new RuntimeException("usernaot found"));

        userr.setStatus(false);

        repository.save(userr);
    }

    public void ActivateUser(Long userId) {
        Userr userr = repository.findById(userId).orElseThrow(()->new RuntimeException("usernaot found"));

        userr.setStatus(true);

        repository.save(userr);
    }

    public Userr updateuser(UserUpdateRiquest request) {
        Userr userr = repository.findById(request.getId()).orElseThrow(null);

        if (userr!=null){
            userr.setFirstName(request.getFirstName());
            userr.setLastName(request.getLastName());
            userr.setPhoneNo(request.getPhoneNo());
            userr.setDepartment(request.getDepartment());
            userr.setTeamLeader(request.getTeamLeader());


            return repository.save(userr);
        }
        else {
            return null;
        }

    }

    public SineUpREquest vewuser(Long id) {
        return repository.findById(id).get().sineUpREquest();
    }

    public Userr updateById(Long id, SineUpREquest rEquest) {

        Userr userr = repository.findById(id).orElseThrow(()-> new RuntimeException("this user is not been found"));

        if (userr != null){

            userr.setFirstName(rEquest.getFirstName());
            userr.setLastName(rEquest.getLastName());
            userr.setPhoneNo(rEquest.getPhoneNo());
            userr.setDepartment(rEquest.getDepartment());
            userr.setTeamLeader(rEquest.getTeamLeader());
            userr.setStatus(rEquest.isStatus());

            return repository.save(userr);
        }
        else {
            return null;
        }
    }
}
