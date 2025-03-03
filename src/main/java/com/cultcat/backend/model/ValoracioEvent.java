package com.cultcat.backend.model;

import com.cultcat.backend.model.id.IdValoracioEvent;
import jakarta.persistence.*;

@Entity
@Table(name = "valoracio_event")
public class ValoracioEvent {
    @EmbeddedId
    private IdValoracioEvent id;

    @ManyToOne
    @MapsId("idUsuari")
    @JoinColumn(name = "id_usuari", nullable = false)
    private Usuari usuari;

    @ManyToOne
    @MapsId("idEvent")
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    @Column(nullable = false)
    private int puntuacio;

    @Column(nullable = false, length = 500)
    private String missatge;

    public ValoracioEvent() {}

    public ValoracioEvent(Usuari usuari, Event event, int puntuacio, String missatge) {
        this.id = new IdValoracioEvent(usuari.getId(), event.getId());
        this.usuari = usuari;
        this.event = event;
        this.puntuacio = puntuacio;
        this.missatge = missatge;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public Event getEvent() {
        return event;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public String getMissatge() {
        return missatge;
    }
}
