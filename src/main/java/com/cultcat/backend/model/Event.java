package com.cultcat.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @Column(nullable = false)
    @JsonProperty("codi")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("data_fi")
    private LocalDate dataFi;

    @Column(nullable = false)
    @JsonProperty("data_inici")
    private LocalDate dataInici;

    @JsonProperty("data_fi_aproximada")
    private String dataFiAproximada;

    @JsonProperty("denominaci")
    private String denominacio;

    private String descripcio;

    private String entrades;

    private String horari;

    @JsonProperty("subt_tol")
    private String subtitol;

    @Column(columnDefinition = "text[]")
    private List<String> tagsAmbits;

    @Column(columnDefinition = "text[]")
    private List<String> tagsCategories;

    @Column(columnDefinition = "text[]")
    private List<String> tagsAltresCategories;

    private String links;

    private String documents;

    private String imatges;

    private String videos;

    @JsonProperty("adre_a")
    private String adreça;

    @JsonProperty("codi_postal")
    private Integer codiPostal;

    @Column(name = "comarca_i_municipi")
    @JsonProperty("comarca_i_municipi")
    private String comarcaIMunicipi;

    private String email;

    private String espai;

    private Float latitud;

    private String localitat;

    private Float longitud;

    @Column(name = "regio_o_pais")
    @JsonProperty("regio_o_pais")
    private String regioOPais;

    @JsonProperty("tel_fon")
    private String telefon;

    private String url;

    @JsonProperty("imgapp")
    private String imgApp;

    @JsonProperty("descripcio_html")
    private String descripcioHtml;

    private String georeferencia;

    private String municipi;

    private String comarca;

    @JsonProperty("amagar_dates")
    private String amagarDates;

    private Long idCreador;

    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataFi() {
        return dataFi;
    }

    public void setDataFi(LocalDate dataFi) {
        this.dataFi = dataFi;
    }

    public LocalDate getDataInici() {
        return dataInici;
    }

    public void setDataInici(LocalDate dataInici) {
        this.dataInici = dataInici;
    }

    public String getDataFiAproximada() {
        return dataFiAproximada;
    }

    public void setDataFiAproximada(String dataFiAproximada) {
        this.dataFiAproximada = dataFiAproximada;
    }

    public String getDenominacio() {
        return denominacio;
    }

    public void setDenominacio(String denominacio) {
        this.denominacio = denominacio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getEntrades() {
        return entrades;
    }

    public void setEntrades(String entrades) {
        this.entrades = entrades;
    }

    public String getHorari() {
        return horari;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public String getSubtitol() {
        return subtitol;
    }

    public void setSubtitol(String subtitol) {
        this.subtitol = subtitol;
    }

    @JsonSetter("tags_mbits")
    public void setTagsAmbitsJson(String tagsAmbitsJson) {
        tagsAmbits = convertTagsToList(tagsAmbitsJson, "agenda:ambits/");
    }

    @JsonSetter("tags_categor_es")
    public void setTagsCategoriesJson(String tagsCategoriesJson) {
        tagsCategories = convertTagsToList(tagsCategoriesJson, "agenda:categories/");
    }

    @JsonSetter("tags_altres_categor_es")
    public void setTagsAltresCategoriesJson(String tagsAltresCategoriesJson) {
        tagsAltresCategories = convertTagsToList(tagsAltresCategoriesJson, "agenda:altres-categories/");
    }

    public List<String> getTagsAmbits() {
        return tagsAmbits;
    }

    public void setTagsAmbits(List<String> tagsAmbits) {
        this.tagsAmbits = tagsAmbits;
    }

    public List<String> getTagsCategories() {
        return tagsCategories;
    }

    public void setTagsCategories(List<String> tagsCategories) {
        this.tagsCategories = tagsCategories;
    }

    public List<String> getTagsAltresCategories() {
        return tagsAltresCategories;
    }

    public void setTagsAltresCategories(List<String> tagsAltresCategories) {
        this.tagsAltresCategories = tagsAltresCategories;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getImatges() {
        return imatges;
    }

    public void setImatges(String imatges) {
        this.imatges = imatges;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public Integer getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(Integer codiPostal) {
        this.codiPostal = codiPostal;
    }

    public String getComarcaIMunicipi() {
        return comarcaIMunicipi;
    }

    public void setComarcaIMunicipi(String comarcaIMunicipi) {
        this.comarcaIMunicipi = comarcaIMunicipi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspai() {
        return espai;
    }

    public void setEspai(String espai) {
        this.espai = espai;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public String getLocalitat() {
        return localitat;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getRegioOPais() {
        return regioOPais;
    }

    public void setRegioOPais(String regioOPais) {
        this.regioOPais = regioOPais;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgApp() {
        return imgApp;
    }

    public void setImgApp(String imgApp) {
        this.imgApp = imgApp;
    }

    public String getDescripcioHtml() {
        return descripcioHtml;
    }

    public void setDescripcioHtml(String descripcioHtml) {
        this.descripcioHtml = descripcioHtml;
    }

    public String getGeoreferencia() {
        return georeferencia;
    }

    public void setGeoreferencia(String georeferencia) {
        this.georeferencia = georeferencia;
    }

    public String getMunicipi() {
        return municipi;
    }

    public void setMunicipi(String municipi) {
        this.municipi = municipi;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getAmagarDates() {
        return amagarDates;
    }

    public void setAmagarDates(String amagarDates) {
        this.amagarDates = amagarDates;
    }

    public void setIdCreador(Long idCreador) {
        this.idCreador = idCreador;
    }

    public Long getIdCreador() {
        return idCreador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);  // Only consider `id` for equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Hash based only on `id`
    }

    @PrePersist
    public void generateId() {
        if (id == null) id = new Random().nextLong(2_000_000_000);
    }

    public static List<String> convertTagsToList(String tags, String commonPart) {
        if (tags == null || tags.isEmpty()) {
            return List.of();
        }

        return Arrays.stream(tags.split(","))
                .map(tag -> tag.replace(commonPart, ""))
                .collect(Collectors.toList());
    }
}
