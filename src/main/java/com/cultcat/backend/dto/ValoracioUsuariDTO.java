package com.cultcat.backend.dto;

import java.time.LocalDateTime;

public interface ValoracioUsuariDTO {
    String getNomUsuariEmissor();
    String getNomUsuariReceptor();
    int getPuntuacio();
    String getMissatge();
    LocalDateTime getData();
}
