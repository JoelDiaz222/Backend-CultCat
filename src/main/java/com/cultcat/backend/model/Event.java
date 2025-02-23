package com.cultcat.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "event")
public class Event implements Serializable {

    public Event() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi", nullable = false)
    private Long codi;

    @Column(name = "data_fi", nullable = false)
    private LocalDate dataFi;

    @Column(name = "data_inici", nullable = false)
    private LocalDate dataInici;

    @Column(name = "data_fi_aproximada")
    private String dataFiAproximada;

    @Column(name = "denominacio")
    private String denominacio;

    @Column(name = "descripcio")
    private String descripcio;

    @Column(name = "entrades")
    private String entrades;

    @Column(name = "horari")
    private String horari;

    @Column(name = "subtitol")
    private String subtitol;

    @Column(name = "tags_ambits", columnDefinition = "text[]")
    private List<String> tagsAmbits;

    @Column(name = "tags_categories", columnDefinition = "text[]")
    private List<String> tagsCategories;

    @Column(name = "tags_altres_categories", columnDefinition = "text[]")
    private List<String> tagsAltresCategories;

    @Column(name = "links")
    private String links;

    @Column(name = "documents")
    private String documents;

    @Column(name = "imatges")
    private String imatges;

    @Column(name = "videos")
    private String videos;

    @Column(name = "adreça")
    private String adreça;

    @Column(name = "codi_postal")
    private Integer codiPostal;

    @Column(name = "comarca_i_municipi")
    private String comarcaIMunicipi;

    @Column(name = "email")
    private String email;

    @Column(name = "espai")
    private String espai;

    @Column(name = "localitat")
    private String localitat;

    @Column(name = "regio_o_pais")
    private String regioOPais;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "url")
    private String url;

    @Column(name = "img_app")
    private String imgApp;

    @Column(name = "descripcio_html")
    private String descripcioHtml;

    @Column(name = "georeferencia")
    private String georeferencia;

    @Column(name = "municipi")
    private String municipi;

    @Column(name = "comarca")
    private String comarca;

    @Column(name = "amagar_dates")
    private String amagarDates;

    public Long getCodi() {
        return codi;
    }

    public void setCodi(Long codi) {
        this.codi = codi;
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

    public String getLocalitat() {
        return localitat;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
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
}
