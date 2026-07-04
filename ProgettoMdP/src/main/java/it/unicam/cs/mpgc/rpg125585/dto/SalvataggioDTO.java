package it.unicam.cs.mpgc.rpg125585.dto;

import java.util.List;

/**
 * DTO per il salvataggio completo della partita.
 * Questa classe fa da aggregatore puro per congelare lo stato del gioco:
 * memorizza le statistiche del giocatore, la struttura della mappa e l'ID
 * della stanza in cui si trova il giocatore nel momento del salvataggio.
 */

public class SalvataggioDTO {

    private GiocatoreDTO giocatore;
    private List<StanzaDTO> mappaStanze;
    private int idStanzaCorrente;

    public SalvataggioDTO() {
    }

    /**
     * Costruttore completo che viene utilizzato per il mapping dei dati quando viene creato un nuovo
     * oggetto di salvataggio da scrivere su file JSON.
     * @param giocatore Il DTO con le statistiche del giocatore.
     * @param mappaStanze La lista dei DTO che rappresentano lo stato attuale di tutte le stanze.
     * @param idStanzaCorrente L'ID numerico della stanza in cui il giocatore si trova.
     */
    public SalvataggioDTO(GiocatoreDTO giocatore, List<StanzaDTO> mappaStanze, int idStanzaCorrente) {
        this.giocatore = giocatore;
        this.mappaStanze = mappaStanze;
        this.idStanzaCorrente = idStanzaCorrente;
    }

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