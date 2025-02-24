package com.cultcat.backend.dto;

import java.io.Serializable;
import java.sql.Date;

public record EventDTO(
        Long id,
        String denominacio,
        String descripcio,
        String adreça,
        Date dataInici,
        Date dataFi) implements Serializable {}
