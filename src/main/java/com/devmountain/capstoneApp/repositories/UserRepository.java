package com.devmountain.capstoneApp.repositories;

import com.devmountain.capstoneApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // this annotation clues spring boot in to keep track of this resource for Dependency Injection
public interface UserRepository extends JpaRepository<User, Long> {
}


// the repository layer is responsible for interacting with the database, and we're using spring data jpa to make this process easier
// the repository layer only interacts with the Service Layer and the Entities