package com.cultcat.backend.controller;

import com.cultcat.backend.dto.ValoracioEventDTO;
import com.cultcat.backend.model.Event;
import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.model.ValoracioEvent;
import com.cultcat.backend.model.id.IdValoracioEvent;
import com.cultcat.backend.repository.EventRepository;
import com.cultcat.backend.repository.ValoracioEventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/valoracions/event")
public class ValoracioEventController {

    private final ValoracioEventRepository valoracioEventRepository;
    private final EventRepository eventRepository;

    public ValoracioEventController(
            ValoracioEventRepository valoracioEventRepository,
            EventRepository eventRepository) {
        this.valoracioEventRepository = valoracioEventRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{idEvent}")
    public List<ValoracioEventDTO> getByEvent(@PathVariable("idEvent") Long idEvent) {
        return valoracioEventRepository.findByEvent(idEvent);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdateValoracio(
            @AuthenticationPrincipal Usuari usuari,
            @RequestParam Long idEvent,
            @RequestParam int puntuacio,
            @RequestParam String missatge) {
        if (puntuacio < 0 || puntuacio > 5)
            return ResponseEntity.badRequest().body("La puntuació ha de ser entre 0 i 5 estrelles.");

        final Optional<Event> optionalEvent = eventRepository.findById(idEvent);

        if (optionalEvent.isEmpty())
            return ResponseEntity.badRequest().body("L'event amb ID " + idEvent + " no s'ha trobat.");

        final Event event = optionalEvent.get();
        final ValoracioEvent valoracio = new ValoracioEvent(usuari, event, puntuacio, missatge);

        valoracioEventRepository.save(valoracio);

        return ResponseEntity.ok("Valoració d'event creada exitosament.");
    }

    @DeleteMapping("/{idEvent}")
    public ResponseEntity<String> deleteValoracio(
            @AuthenticationPrincipal Usuari usuari,
            @PathVariable Long idEvent) {
        final IdValoracioEvent id = new IdValoracioEvent(usuari.getId(), idEvent);
        final Optional<ValoracioEvent> optionalValoracio = valoracioEventRepository.findById(id);

        if (optionalValoracio.isEmpty())
            return ResponseEntity.badRequest().body("No s'ha trobat la valoració per aquest event.");

        final ValoracioEvent valoracio = optionalValoracio.get();

        if (!valoracio.getUsuari().getId().equals(usuari.getId()) &&
                !usuari.getRols().contains("ADMIN"))
            return ResponseEntity.status(403).body("Només pots esborrar les teves pròpies valoracions.");

        valoracioEventRepository.delete(valoracio);
        return ResponseEntity.ok("Valoració eliminada exitosament.");
    }
}
