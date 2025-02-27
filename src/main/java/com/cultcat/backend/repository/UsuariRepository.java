package com.cultcat.backend.repository;

import com.cultcat.backend.model.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuariRepository extends JpaRepository<Usuari, String> {
    Optional<Usuari> findByGoogleId(String googleId);

    Optional<Usuari> findByEmail(String email);

    Optional<Usuari> findByNomUsuari(String nomUsuari);
}
