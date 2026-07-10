package it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.entita.EntitaGenerale;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaGenerica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Giocatore extends EntitaGenerale{

    private StanzaGenerica stanzaCorrente;
    private final List<Artefatto> inventario = new ArrayList<>();
    private final int puntiAttaccoBase;

    public Giocatore(int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        super(vitaMassima, puntiVita, puntiAttacco, puntiScudo);
        this.puntiAttaccoBase = puntiAttacco;
    }

    public StanzaGenerica getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public void setStanzaCorrente(StanzaGenerica nuovaStanza) {
        this.stanzaCorrente = nuovaStanza;
    }

    /**
     * Incrementa i punti attacco del giocatore in base al danno dell'arma raccolta.
     */
    public void aumentaAttaccoDaArma(int bonusDanno) {
        if (bonusDanno > 0) {
            this.puntiAttacco += bonusDanno;
        }
    }

    public void aggiungiAllInventario(Artefatto artefatto) {
        if (artefatto != null) {
            this.inventario.add(artefatto);
        }
    }

    public List<Artefatto> getInventario() {
        return Collections.unmodifiableList(this.inventario);
    }

    public int getPuntiAttaccoBase() {
        return this.puntiAttaccoBase;
    }

    public void resetPuntiAttaccoBase() {
        this.puntiAttacco = this.puntiAttaccoBase;
    }
}