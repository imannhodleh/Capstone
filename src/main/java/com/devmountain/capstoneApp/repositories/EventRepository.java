package com.devmountain.capstoneApp.repositories;

import com.devmountain.capstoneApp.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}


// the repository layer is responsible for interacting with the database
// we're using spring data jpa to make this process easier