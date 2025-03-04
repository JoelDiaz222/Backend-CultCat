package com.cultcat.backend.model;

import com.cultcat.backend.model.id.IdValoracioUsuari;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "valoracio_usuari")
public class ValoracioUsuari {
    @EmbeddedId
    private IdValoracioUsuari id;

    @ManyToOne
    @MapsId("idUsuariEmissor")
    @JoinColumn(name = "id_usuari_emissor", nullable = false)
    private Usuari usuariEmissor;

    @ManyToOne
    @MapsId("idUsuariReceptor")
    @JoinColumn(name = "id_usuari_receptor", nullable = false)
    private Usuari usuariReceptor;

    @Column(nullable = false)
    private int puntuacio;

    @Column(nullable = false, length = 500)
    private String missatge;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime data;

    public ValoracioUsuari() {}

    public ValoracioUsuari(Usuari usuariEmissor, Usuari usuariReceptor, int puntuacio, String missatge) {
        this.id = new IdValoracioUsuari(usuariEmissor.getId(), usuariReceptor.getId());
        this.usuariEmissor = usuariEmissor;
        this.usuariReceptor = usuariReceptor;
        this.puntuacio = puntuacio;
        this.missatge = missatge;
    }

    public IdValoracioUsuari getId() {
        return id;
    }

    public void setId(IdValoracioUsuari id) {
        this.id = id;
    }

    public Usuari getUsuariEmissor() {
        return usuariEmissor;
    }

    public void setUsuariEmissor(Usuari usuariEmissor) {
        this.usuariEmissor = usuariEmissor;
    }

    public Usuari getUsuariReceptor() {
        return usuariReceptor;
    }

    public void setUsuariReceptor(Usuari usuariReceptor) {
        this.usuariReceptor = usuariReceptor;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public LocalDateTime getData() {
        return data;
    }
}
