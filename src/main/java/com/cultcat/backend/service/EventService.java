package com.cultcat.backend.service;

import com.cultcat.backend.dto.EventDTO;
import com.cultcat.backend.model.Event;
import com.cultcat.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Optional<Event> getEventById(long id) {
        return eventRepository.findById(id);
    }

    public List<EventDTO> getEventsWithinRadiusAndDate(
            double latitude,
            double longitude,
            double radius,
            LocalDate from,
            LocalDate to) {
        return eventRepository.findByLocationWithinRadiusAndDate(
                latitude,
                longitude,
                radius,
                from,
                to);
    }
}
