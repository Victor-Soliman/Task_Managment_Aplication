package com.nasr.TaskNS.repository;

import com.nasr.TaskNS.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByUsernameAndPassword(String name, String password);

//    Optional<Users> findByEmail(String email); // for security

    // security
    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndPassword(String username,String password);

    Users findByEmail(String email);
}
