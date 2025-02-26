package com.cultcat.backend.service;

import com.cultcat.backend.dto.EventDTO;
import com.cultcat.backend.model.Event;
import com.cultcat.backend.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

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
}
