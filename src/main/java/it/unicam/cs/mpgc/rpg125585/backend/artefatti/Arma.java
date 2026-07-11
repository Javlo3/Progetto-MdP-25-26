package it.unicam.cs.mpgc.rpg125585.backend.artefatti;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;

public class Arma extends Artefatto{

    private final int dannoArma;

    public Arma(String nomeArtefatto, String descrizioneArtefatto, int dannoArma) {
        super(nomeArtefatto, descrizioneArtefatto);
        this.dannoArma = dannoArma;
    }

    public int getDannoArma() {
        return dannoArma;
    }

    public void equipaggiaA(Giocatore giocatore) {
        giocatore.aumentaAttaccoDaArma(this.dannoArma);
    }

    public void applicaEffetto(Giocatore giocatore) {
        // Ripristina l'attacco standard prima di sommare il bonus della nuova arma equipaggiata
        giocatore.resetPuntiAttaccoBase();
        equipaggiaA(giocatore);
        giocatore.aggiungiAllInventario(this);
    }
}