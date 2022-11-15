package com.devmountain.capstoneApp.repositories;

import com.devmountain.capstoneApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // this annotation clues spring boot in to keep track of this resource for Dependency Injection
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
