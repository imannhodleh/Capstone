package com.devmountain.capstoneApp.entities;

import com.devmountain.capstoneApp.dtos.EventDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Integer location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Event() {
    }

    public Event(Long id, String title, String description, Integer location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    @ManyToOne // this annotation creates the association within Hibernate
    @JsonBackReference // this annotation prevents infinite recursion when you deliver the resource up as JSON through the RESTful API endpoint I'll create
    private User user;

    public void setUser(User user) {
        System.out.println(user.getUsername());
        this.user = user;
    }

    public User getUser() {
        return user;
    }



    public Event(EventDto eventDto) {
        if (eventDto.getTitle() != null) {
            this.title = eventDto.getTitle();
        }
        if (eventDto.getDescription() != null) {
            this.description = eventDto.getDescription();
        }
        if (eventDto.getLocation() != null) {
            this.location = eventDto.getLocation();
        }
    }
}
