package com.esprit.pifirstversion.repository;

import com.esprit.pifirstversion.models.UserAPP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserAPP,Integer> {
    Optional<UserAPP> findByUsername(String username);
}
