package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;

/**
 * Data Transfer Object (DTO) per la classe Giocatore.
 * Questa classe è una struttura dati pura utilizzata esclusivamente per la
 * persistenza (salvataggio e caricamento) dei dati tramite Gson, evitando
 * riferimenti circolari e la serializzazione di oggetti complessi come le stanze.
 */
public class GiocatoreDTO {

    private String tipoClasse; // Memorizza la classe specifica (es. "Cavaliere", "Arciere", "Mago")
    private int vitaMassima;
    private int puntiVita;
    private int puntiAttacco;
    private int puntiScudo;

    /**
     * Costruttore vuoto/di default richiesto da alcune librerie di serializzazione (come Gson)
     * per istanziare l'oggetto tramite reflection.
     */
    public GiocatoreDTO() {
    }

    /**
     * Costruttore completo utilizzato principalmente in fase di caricamento dal JSON
     * per mappare esplicitamente tutti i campi primitivi.
     */
    public GiocatoreDTO(String tipoClasse, int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        this.tipoClasse = tipoClasse;
        this.vitaMassima = vitaMassima;
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiScudo = puntiScudo;
    }

    /**
     * Costruttore di convenienza (Factory/Mapping) utilissimo per il salvataggio.
     * Prende il giocatore in memoria e ne estrae automaticamente lo stato primitivo.
     * * @param giocatore L'istanza del giocatore attivo nel gioco.
     */
    public GiocatoreDTO(Giocatore giocatore) {
        this.tipoClasse = giocatore.getClass().getSimpleName(); // Estrae "Cavaliere", "Arciere", ecc.
        this.vitaMassima = giocatore.getVitaMassima();
        this.puntiVita = giocatore.getPuntiVita();
        this.puntiAttacco = giocatore.getPuntiAttacco();
        this.puntiScudo = giocatore.getPuntiScudo();
    }

    // --- GETTER (Essenziali per rileggere i dati nel GestoreFile in fase di CARICAMENTO) ---

    public String getTipoClasse() {
        return tipoClasse;
    }

    public int getVitaMassima() {
        return vitaMassima;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiAttacco() {
        return puntiAttacco;
    }

    public int getPuntiScudo() {
        return puntiScudo;
    }
}