package com.cultcat.backend.controller;

import com.cultcat.backend.dto.EventDTO;
import com.cultcat.backend.model.Event;
import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.EventRepository;
import com.cultcat.backend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;
    private final EventService eventService;

    public EventController(EventRepository eventRepository, EventService eventService) {
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        final Optional<Event> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<EventDTO> getEventsWithinRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to) {

        return eventRepository.findByLocationWithinRadiusAndDate(latitude, longitude, radius, from, to);
    }

    @PostMapping
    public ResponseEntity<String> createEvent(
            @AuthenticationPrincipal final Usuari usuari,
            @RequestBody final Event event) {

        event.setIdCreador(usuari.getId());
        eventRepository.save(event);

        return ResponseEntity.ok("Event creat exitosament.");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @eventController.isEventOwner(principal, #id)")
    public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event event) {
        final Optional<Event> existingEvent = eventService.getEventById(id);

        if (existingEvent.isPresent()) {
            event.setId(id);
            final Event updatedEvent = eventService.updateEvent(event);
            return ResponseEntity.ok(updatedEvent);
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @eventController.isEventOwner(principal, #id)")
    public ResponseEntity<String> deleteEvent(
            @AuthenticationPrincipal Usuari usuari,
            @PathVariable("id") Long id) {

        final Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isEmpty()) return ResponseEntity.notFound().build();

        final Event event = eventOptional.get();

        if (event.getIdCreador() == null || !event.getIdCreador().equals(usuari.getId())) {
            return ResponseEntity.status(403).body("No estàs autoritzat per eliminar aquest event.");
        }

        eventRepository.delete(event);
        return ResponseEntity.ok("Event eliminat exitosament.");
    }

    @GetMapping("/update")
    public String insertNewEventsFromDataset() {
        return "New events inserted: " + eventService.fetchAndStoreNewEvents();
    }

    public boolean isEventOwner(final Usuari usuari, final Long eventId) {
        final Optional<Event> event = eventRepository.findById(eventId);
        return event.isPresent() && event.get().getIdCreador().equals(usuari.getId());
    }
}
