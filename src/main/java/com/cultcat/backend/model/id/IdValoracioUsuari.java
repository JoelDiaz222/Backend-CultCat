package com.cultcat.backend.model.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IdValoracioUsuari implements Serializable {
    private Long idUsuariEmissor;
    private Long idUsuariReceptor;

    public IdValoracioUsuari() {}

    public IdValoracioUsuari(Long idUsuariEmissor, Long idUsuariReceptor) {
        this.idUsuariEmissor = idUsuariEmissor;
        this.idUsuariReceptor = idUsuariReceptor;
    }

    public Long getidUsuariEmissor() {
        return idUsuariEmissor;
    }

    public void setidUsuariEmissor(Long idUsuariEmissor) {
        this.idUsuariEmissor = idUsuariEmissor;
    }

    public Long getidUsuariReceptor() {
        return idUsuariReceptor;
    }

    public void setidUsuariReceptor(Long idUsuariReceptor) {
        this.idUsuariReceptor = idUsuariReceptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdValoracioUsuari that = (IdValoracioUsuari) o;
        return Objects.equals(idUsuariEmissor, that.idUsuariEmissor) &&
                Objects.equals(idUsuariReceptor, that.idUsuariReceptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuariEmissor, idUsuariReceptor);
    }
}
