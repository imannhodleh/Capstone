package com.devmountain.capstoneApp.services;

import com.devmountain.capstoneApp.dtos.EventDto;
import com.devmountain.capstoneApp.dtos.UserDto;
import com.devmountain.capstoneApp.entities.Event;
import com.devmountain.capstoneApp.entities.User;
import com.devmountain.capstoneApp.repositories.EventRepository;
import com.devmountain.capstoneApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// what would an event need to do in terms of CRUD operations
// should be able to find all events by User
// should be able to add an event, update an event, delete an event, and find an event by id
@Service
public class EventServiceImpl implements EventService {
    @Autowired // my 2 autowired dependencies
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;


// adding an event
@Override
@Transactional
public void addEvent(EventDto eventDto, Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    Event event = new Event(eventDto);
    userOptional.ifPresent(event::setUser);
    eventRepository.saveAndFlush(event);
}

// deleting an event
@Override
@Transactional
public void deleteEventById(Long eventId) {
    Optional<Event> eventOptional = eventRepository.findById(eventId);
    eventOptional.ifPresent(event -> eventRepository.delete(event));
}

// updating an event
@Override
@Transactional
public void updateEventById(EventDto eventDto) {
    Optional<Event> eventOptional = eventRepository.findById(eventDto.getId());
    eventOptional.ifPresent(event -> {
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        event.setLocation(eventDto.getLocation());
        eventRepository.saveAndFlush(event);
    });
}

// getting all events by the user
@Override
public List<EventDto> getAllEventsByUserId(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
        List<Event> eventList = eventRepository.findAllByUserEquals(userOptional.get());
        return eventList.stream().map(event -> new EventDto(event)).collect(Collectors.toList());
    }
    return Collections.emptyList();
}

// getting an event by the event id
@Override
public Optional<EventDto> getEventById(Long eventId) {
    Optional<Event> eventOptional = eventRepository.findById(eventId);
    if (eventOptional.isPresent()) {
        return Optional.of(new EventDto(eventOptional.get()));
    }
    return Optional.empty();
}
}
