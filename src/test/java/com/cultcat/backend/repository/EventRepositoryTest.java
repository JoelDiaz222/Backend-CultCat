package com.cultcat.backend.repository;

import com.cultcat.backend.model.Event;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    final static Long idCreador = 1L;

    @BeforeAll
    static void setUp() {
        System.setProperty("DB_URL", System.getenv("DB_URL"));
    }

    @Test
    void testInsertEvent() {
        Event event = new Event();
        event.setDataFi(LocalDate.of(2025, 12, 31));
        event.setDataInici(LocalDate.of(2025, 12, 1));
        event.setDenominacio("Test " + UUID.randomUUID());
        event.setDescripcio("Un concert espectacular.");
        event.setTagsAmbits(List.of("concerts", "festivals-i-mostres"));
        event.setIdCreador(idCreador);

        Event savedEvent = eventRepository.save(event);

        assertNotNull(savedEvent.getId(), "Saved event should have an ID");

        List<Event> events = eventRepository.findByIdCreador(idCreador);
        assertFalse(events.isEmpty(), "Event list should not be empty");

        Event retrievedEvent = events.stream()
                .filter(e -> e.getId().equals(savedEvent.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(retrievedEvent, "Saved event should be found in the database");

        assertEquals(savedEvent.getDataFi(), retrievedEvent.getDataFi(), "End date should match");
        assertEquals(savedEvent.getDataInici(), retrievedEvent.getDataInici(), "Start date should match");
        assertEquals(savedEvent.getDenominacio(), retrievedEvent.getDenominacio(), "Name should match");
        assertEquals(savedEvent.getDescripcio(), retrievedEvent.getDescripcio(), "Description should match");
        assertEquals(savedEvent.getTagsAmbits(), retrievedEvent.getTagsAmbits(), "Tags should match");
        assertEquals(savedEvent.getIdCreador(), retrievedEvent.getIdCreador(), "Creator ID should match");

        eventRepository.delete(savedEvent);
        assertFalse(eventRepository.findById(savedEvent.getId()).isPresent(), "Event should be deleted");
    }
}
