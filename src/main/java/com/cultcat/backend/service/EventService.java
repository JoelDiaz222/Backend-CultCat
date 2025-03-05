package com.cultcat.backend.service;

import com.cultcat.backend.model.Event;
import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    private static final String API_URL = "https://analisi.transparenciacatalunya.cat/resource/rhpv-yr4f.json?$where=codi > ";
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;
    private final RestTemplate restTemplate;

    public EventService(EventRepository eventRepository, RestTemplate restTemplate) {
        this.eventRepository = eventRepository;
        this.restTemplate = restTemplate;
    }

    public Optional<Event> getEventById(long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public ResponseEntity<String> deleteEvent(Usuari usuari, Long id) {
        final Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isEmpty()) return ResponseEntity.notFound().build();

        final Event event = eventOptional.get();

        if (event.getIdCreador() == null || !event.getIdCreador().equals(usuari.getId()))
            return ResponseEntity.status(403).body("No estàs autoritzat per eliminar aquest event.");

        eventRepository.delete(event);
        return ResponseEntity.ok("Event eliminat exitosament.");
    }

    @Scheduled(cron = "0 0 */12 * * *")
    public int fetchAndStoreNewEvents() {
        try {
            logger.info("Starting to update the events");

            Long minId = eventRepository.findMaxId().orElse(0L);

            String requestUrl = API_URL + minId;
            Event[] eventsArray = restTemplate.getForObject(requestUrl, Event[].class);

            assert eventsArray != null;

            int insertedEvents = eventsArray.length;
            if (insertedEvents > 0) {
                Set<Event> eventSet = new HashSet<>(Arrays.asList(eventsArray));
                insertedEvents = eventSet.size();
                eventRepository.saveAll(eventSet);
            }

            logger.info("Inserted " + insertedEvents + " new events");

            return insertedEvents;
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> findByFilters(
            String name, List<String> tagsAmbits,
            List<String> tagsCategories,
            List<String> tagsAltresCategories) {
        if (tagsAmbits == null) tagsAmbits = List.of();
        if (tagsCategories == null) tagsCategories = List.of();
        if (tagsAltresCategories == null) tagsAltresCategories = List.of();

        final String formattedTagsAmbits = formatAsPostgresArray(tagsAmbits);
        final String formattedTagsCategories = formatAsPostgresArray(tagsCategories);
        final String formattedTagsAltresCategories = formatAsPostgresArray(tagsAltresCategories);

        return eventRepository.findByFilters(
                name,
                formattedTagsAmbits,
                formattedTagsCategories,
                formattedTagsAltresCategories
        );
    }

    public static String formatAsPostgresArray(List<String> tags) {
        return "{" + tags.stream().map(tag -> "\"" + tag + "\"").collect(Collectors.joining(",")) + "}";
    }
}
