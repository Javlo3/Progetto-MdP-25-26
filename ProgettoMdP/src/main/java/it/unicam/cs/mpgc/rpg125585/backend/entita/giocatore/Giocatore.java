package it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backend.entita.EntitaGenerale;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaGenerica;

public abstract class Giocatore extends EntitaGenerale{

    private StanzaGenerica stanzaCorrente;

    public Giocatore(int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        super(vitaMassima, puntiVita, puntiAttacco, puntiScudo);
    }

    public StanzaGenerica getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public void setStanzaCorrente(StanzaGenerica nuovaStanza) {
        this.stanzaCorrente = nuovaStanza;
    }

    public void setPuntiAttacco(int puntiAttacco) {
        this.puntiAttacco = puntiAttacco;
    }

    /**
     * Incrementa i punti attacco del giocatore in base al danno dell'arma raccolta.
     */
    public void aumentaAttaccoDaArma(int bonusDanno) {
        if (bonusDanno > 0) {
            this.puntiAttacco += bonusDanno;
        }
    }
}