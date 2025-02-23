package com.cultcat.backend.service;

import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariService {
    @Autowired
    private UsuariRepository usuariRepository;

    public Optional<Usuari> findByGoogleId(String googleId) {
        return usuariRepository.findByGoogleId(googleId);
    }
}
