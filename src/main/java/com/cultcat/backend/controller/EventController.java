package com.cultcat.backend.controller;

import com.cultcat.backend.dto.EventDTO;
import com.cultcat.backend.model.Event;
import com.cultcat.backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/search")
    public List<EventDTO> getEventsWithinRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {
        if (from != null && to != null && from.isAfter(to))
            throw new IllegalArgumentException("La data d'inici ha de ser posterior a la de fi.");
        return eventService.getEventsWithinRadiusAndDate(latitude, longitude, radius, from, to);
    }

    @GetMapping("/update")
    public String insertNewEventsFromDataset() {
        return "New events inserted: " + eventService.fetchAndStoreNewEvents();
    }
}
