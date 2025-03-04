package com.cultcat.backend.repository;

import com.cultcat.backend.dto.ValoracioUsuariDTO;
import com.cultcat.backend.model.ValoracioUsuari;
import com.cultcat.backend.model.id.IdValoracioUsuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValoracioUsuariRepository extends JpaRepository<ValoracioUsuari, IdValoracioUsuari> {

    @Query("""
        SELECT v.usuariEmissor.nomUsuari AS nomUsuariEmissor,
               v.usuariReceptor.nomUsuari AS nomUsuariReceptor,
               v.puntuacio AS puntuacio,
               v.missatge AS missatge,
               v.data AS data
        FROM ValoracioUsuari v
        WHERE v.usuariReceptor.id = :idUsuariReceptor
    """)
    List<ValoracioUsuariDTO> findByReceptor(@Param("idUsuariReceptor") Long idUsuariReceptor);

    @Query("""
        SELECT v.usuariEmissor.nomUsuari AS nomUsuariEmissor,
               v.usuariReceptor.nomUsuari AS nomUsuariReceptor,
               v.puntuacio AS puntuacio,
               v.missatge AS missatge,
               v.data AS data
        FROM ValoracioUsuari v
        WHERE v.usuariEmissor.id = :idUsuariEmissor
    """)
    List<ValoracioUsuariDTO> findByEmissor(@Param("idUsuariEmissor") Long idUsuariEmissor);
}
