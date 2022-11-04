package com.devmountain.capstoneApp.services;

import com.devmountain.capstoneApp.dtos.EventDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventService {
    // adding an event
    @Transactional
    void addEvent(EventDto eventDto, Long userId);

    // deleting an event
    @Transactional
    void deleteEventById(Long eventId);

    // updating an event
    @Transactional
    void updateEventById(EventDto eventDto);

    // getting all events by the user
    List<EventDto> getAllEventsByUserId(Long userId);

    // getting an event by the event id
    Optional<EventDto> getEventById(Long eventId);
}
