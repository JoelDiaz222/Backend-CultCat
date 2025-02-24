package com.cultcat.backend.repository;

import com.cultcat.backend.dto.EventDTO;
import com.cultcat.backend.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = """
        SELECT e.id, e.denominacio, e.descripcio, e.adreça, e.data_inici, e.data_fi
        FROM event e
        WHERE ST_DWithin(e.localitzacio, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radius)
        AND (CAST(:from AS DATE) IS NULL OR e.data_inici >= CAST(:from AS DATE))
        AND (CAST(:to AS DATE) IS NULL OR e.data_fi <= CAST(:to AS DATE))
        """, nativeQuery = true)
    List<EventDTO> findByLocationWithinRadiusAndDate(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radius") double radius,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);

    List<Event> findByIdCreador(Long idCreador);
}
