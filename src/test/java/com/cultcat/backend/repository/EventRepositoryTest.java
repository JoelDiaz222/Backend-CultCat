package com.cultcat.backend.repository;

import com.cultcat.backend.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testInsertEvent() {
        Event event = new Event();
        event.setDataFi(LocalDate.of(2025, 12, 31));
        event.setDataInici(LocalDate.of(2025, 12, 1));
        event.setDenominacio("Test concert");
        event.setDescripcio("Un concert espectacular.");
        event.setTagsAmbits(List.of(new String[]{"concerts", "festivals-i-mostres"}));

        eventRepository.save(event);
    }
}
