package com.cultcat.backend.repository;

import com.cultcat.backend.model.ValoracioEvent;
import com.cultcat.backend.model.id.IdValoracioEvent;
import com.cultcat.backend.dto.ValoracioEventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ValoracioEventRepository extends JpaRepository<ValoracioEvent, IdValoracioEvent> {

    @Query("""
        SELECT v.usuari.nomUsuari AS nomUsuari,
            v.event.denominacio AS nomEvent,
            v.puntuacio AS puntuacio,
            v.missatge AS missatge,
            v.data AS data
            FROM ValoracioEvent v WHERE v.event.id = :eventId
    """)
    List<ValoracioEventDTO> findByEvent(Long eventId);

    @Query("""
        SELECT v.usuari.nomUsuari AS nomUsuari,
                    v.event.denominacio AS nomEvent,
                    v.puntuacio AS puntuacio,
                    v.missatge AS missatge,
                    v.data AS data
                    FROM ValoracioEvent v WHERE v.usuari.id = :userId
        """)
    List<ValoracioEventDTO> findByUsuari(Long userId);
}
