package it.unicam.cs.mpgc.rpg125585.dto;

import java.util.List;

// Aggregatore per congelare lo stato del gioco (Giocatore, Mappa e Posizione)

public class SalvataggioDTO {

    private GiocatoreDTO giocatore;
    private List<StanzaDTO> mappaStanze;
    private int idStanzaCorrente;

    // Costruttore vuoto per la deserializzazione GSON

    public SalvataggioDTO() {}

    // Costruttore completo per il mapping del backend verso il file json

    public SalvataggioDTO(GiocatoreDTO giocatore, List<StanzaDTO> mappaStanze, int idStanzaCorrente) {
        this.giocatore = giocatore;
        this.mappaStanze = mappaStanze;
        this.idStanzaCorrente = idStanzaCorrente;
    }

    // Getter e Setter per la persistenza dei dati

    public GiocatoreDTO getGiocatore() {
        return giocatore;
    }

    public List<StanzaDTO> getMappaStanze() {
        return mappaStanze;
    }

    public int getIdStanzaCorrente() {
        return idStanzaCorrente;
    }

    public void setGiocatore(GiocatoreDTO giocatore) {
        this.giocatore = giocatore;
    }

    public void setMappaStanze(List<StanzaDTO> mappaStanze) {
        this.mappaStanze = mappaStanze;
    }

    public void setIdStanzaCorrente(int idStanzaCorrente) {
        this.idStanzaCorrente = idStanzaCorrente;
    }
}