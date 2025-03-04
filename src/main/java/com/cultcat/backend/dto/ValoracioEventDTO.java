package com.cultcat.backend.dto;

import java.time.LocalDateTime;

public interface ValoracioEventDTO {
    String getNomUsuari();
    String getNomEvent();
    Integer getPuntuacio();
    String getMissatge();
    LocalDateTime getData();
}
