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
        giocatore.aumentaAttaccoDaArma(this.dannoArma); // L'incapsulamento è salvo
    }

    public void applicaEffetto(Giocatore giocatore) {
        giocatore.resetPuntiAttaccoBase();
        // Aggiunge il danno di questa specifica arma
        giocatore.aumentaAttaccoDaArma(this.dannoArma);
        // Finisce nell'inventario
        giocatore.aggiungiAllInventario(this);
        System.out.println("Equipaggiata arma: " + getNomeArtefatto() + "! Attacco: " + giocatore.getPuntiAttacco());
    }
}