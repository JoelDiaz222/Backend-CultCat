package com.cultcat.backend.model.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IdValoracioEvent implements Serializable {
    private Long idUsuari;
    private Long idEvent;

    public IdValoracioEvent() {}

    public IdValoracioEvent(Long idUsuari, Long idEvent) {
        this.idUsuari = idUsuari;
        this.idEvent = idEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdValoracioEvent that = (IdValoracioEvent) o;
        return Objects.equals(idUsuari, that.idUsuari) &&
                Objects.equals(idEvent, that.idEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuari, idEvent);
    }
}
