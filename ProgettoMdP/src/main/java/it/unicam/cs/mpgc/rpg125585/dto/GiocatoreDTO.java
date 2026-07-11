package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;

// Rappresentazione leggera del giocatore per il trasferimento dati e la persistenza

public class GiocatoreDTO {

    private String tipoClasse; // Memorizza la classe specifica (es. "Cavaliere", "Arciere", "Mago")
    private int vitaMassima;
    private int puntiVita;
    private int puntiAttacco;
    private int puntiScudo;


    // Costruttore per la creazione e la deserializzazione GSON

    public GiocatoreDTO() {
    }

    public GiocatoreDTO(String tipoClasse, int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        this.tipoClasse = tipoClasse;
        this.vitaMassima = vitaMassima;
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiScudo = puntiScudo;
    }

    public GiocatoreDTO(Giocatore giocatore) {
        this.tipoClasse = giocatore.getClass().getSimpleName(); // Estrae "Cavaliere", "Arciere", ecc.
        this.vitaMassima = giocatore.getVitaMassima();
        this.puntiVita = giocatore.getPuntiVita();
        this.puntiAttacco = giocatore.getPuntiAttacco();
        this.puntiScudo = giocatore.getPuntiScudo();
    }

    // Getter per la gestione dei dati nell'interfaccia grafica

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