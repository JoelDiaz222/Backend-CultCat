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

@SpringBootTest
public class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    final static Long idCreador = 1L;

    @BeforeAll
    static void setUp() {
        System.setProperty("DB_URL", Dotenv.load().get("DB_URL"));
    }

    @Test
    void testInsertEvent() {
        Event event = new Event();
        event.setDataFi(LocalDate.of(2025, 12, 31));
        event.setDataInici(LocalDate.of(2025, 12, 1));
        event.setDenominacio("Test " + UUID.randomUUID());
        event.setDescripcio("Un concert espectacular.");
        event.setTagsAmbits(List.of(new String[]{"concerts", "festivals-i-mostres"}));
        event.setIdCreador(idCreador);

        eventRepository.save(event);
        eventRepository.findByIdCreador(idCreador).orElseThrow();
    }
}
