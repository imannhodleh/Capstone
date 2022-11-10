package com.devmountain.capstoneApp.dtos;

import com.devmountain.capstoneApp.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable; // implemented this interface to allow the class to be converted to a Byte-stream and sent outside the application or stored in a log file
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer location;

    public EventDto(Event event) {
        if (event.getId() != null) {
            this.id = event.getId();
        }
        if (event.getTitle() != null) {
            this.title = event.getTitle();
        }
        if (event.getDescription() != null) {
            this.description = event.getDescription();
        }
        if (event.getLocation() != null) {
            this.location = event.getLocation();
        }
    }

}
