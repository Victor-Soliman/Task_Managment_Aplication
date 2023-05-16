package com.nasr.TaskNS.repository;

import com.nasr.TaskNS.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


    Boolean existsByEmail(String email);

    // security
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    @Query(value = "SELECT username FROM users WHERE user_id =:id", nativeQuery = true)
    String findNameOfUserById(Long id);
}
