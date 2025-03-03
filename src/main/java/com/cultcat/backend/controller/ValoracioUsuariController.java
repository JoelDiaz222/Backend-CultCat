package com.cultcat.backend.controller;

import com.cultcat.backend.dto.ValoracioUsuariDTO;
import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.model.ValoracioUsuari;
import com.cultcat.backend.model.id.IdValoracioUsuari;
import com.cultcat.backend.repository.UsuariRepository;
import com.cultcat.backend.repository.ValoracioUsuariRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/valoracions/usuari")
public class ValoracioUsuariController {

    private final ValoracioUsuariRepository valoracioUsuariRepository;
    private final UsuariRepository usuariRepository;

    private ValoracioUsuariController(
            ValoracioUsuariRepository valoracioUsuariRepository,
            UsuariRepository usuariRepository) {
        this.valoracioUsuariRepository = valoracioUsuariRepository;
        this.usuariRepository = usuariRepository;
    }

    @GetMapping("/receptor/{id}")
    public List<ValoracioUsuariDTO> getByReceptor(@PathVariable("id") Long id) {
        return valoracioUsuariRepository.findByReceptor(id);
    }

    @GetMapping("/emissor/{id}")
    public List<ValoracioUsuariDTO> getByEmissor(@PathVariable("id") Long id) {
        return valoracioUsuariRepository.findByEmissor(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdateValoracio(
            @AuthenticationPrincipal Usuari usuari,
            @RequestParam Long idUsuariReceptor,
            @RequestParam int puntuacio,
            @RequestParam String missatge) {
        if (puntuacio < 0 || puntuacio > 5)
            throw new IllegalArgumentException("La puntuació ha de ser entre 0 i 5 estrelles.");

        Optional<Usuari> optionalUsuariReceptor = usuariRepository.findById(idUsuariReceptor);

        if (optionalUsuariReceptor.isEmpty())
            return ResponseEntity.badRequest().body("L'usuari amb ID " + idUsuariReceptor + " no s'ha trobat.");

        Usuari usuariReceptor = optionalUsuariReceptor.get();

        if (usuari.getId().equals(usuariReceptor.getId()))
            return ResponseEntity.badRequest().body("No pots valorar-te a tú mateix/a.");

        ValoracioUsuari valoracio = new ValoracioUsuari(usuari, usuariReceptor, puntuacio, missatge);
        valoracioUsuariRepository.save(valoracio);

        return ResponseEntity.ok("Valoració creada exitosament.");
    }

    @DeleteMapping("/{idUsuariReceptor}")
    public ResponseEntity<String> deleteValoracio(
            @AuthenticationPrincipal Usuari usuari,
            @PathVariable Long idUsuariReceptor) {
        IdValoracioUsuari id = new IdValoracioUsuari(usuari.getId(), idUsuariReceptor);
        Optional<ValoracioUsuari> optionalValoracio = valoracioUsuariRepository.findById(id);

        if (optionalValoracio.isEmpty())
            return ResponseEntity.badRequest().body("La valoració a l'usuari " + idUsuariReceptor + " no s'ha trobat.");

        ValoracioUsuari valoracio = optionalValoracio.get();

        if (!valoracio.getUsuariEmissor().getId().equals(usuari.getId()) &&
                !usuari.getRols().contains("ADMIN"))
            return ResponseEntity.status(403).body("Només pots esborrar les teves valoracions a altres usuaris.");

        valoracioUsuariRepository.delete(valoracio);
        return ResponseEntity.ok("Valoració esborrada exitosament.");
    }
}
