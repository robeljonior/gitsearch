package com.example.serchrepo.Repository;

import com.example.serchrepo.Model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserrRepo extends JpaRepository<Userr,Long> {

    Userr findByFirstName(String userName);

    Optional<Userr> findByEmail(String email);


    boolean existsUserrByUserName(String username);

    Userr findByUserName(String username);



    boolean existsUserrByEmail(String email);



    Userr findByGitToken(String userRepoToken);


//    boolean existsUserrByPhoneNo(String firstName);


}
