package com.devmountain.capstoneApp.controllers;

import com.devmountain.capstoneApp.dtos.EventDto;
import com.devmountain.capstoneApp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventService eventService;

// get all events by user
@GetMapping("/user/{userId}")
public List<EventDto> getEventsByUser(@PathVariable Long userId) {
    return eventService.getAllEventsByUserId(userId);
}

// add a new event
@PostMapping("/user/{userId}")
public void addEvent(@RequestBody EventDto eventDto, @PathVariable Long userId) {
    eventService.addEvent(eventDto, userId);
}

// delete an event
@DeleteMapping("/{eventId}")
public void deleteEventById(@PathVariable Long eventId) {
    eventService.deleteEventById(eventId);
}

// update an existing event
@PutMapping
public void updateEvent(@RequestBody EventDto eventDto) {
    eventService.updateEventById(eventDto);
}

// get an event by the event's id
@GetMapping("/{eventId}")
public Optional<EventDto> getEventById(@PathVariable Long eventId){
    return eventService.getEventById(eventId);
}
}

// there should be a method corresponding with each listed method in the EventService file, 5