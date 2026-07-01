package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;

public class Arma extends Artefatto{

    private final int dannoArma;

    public Arma(String nome, String descrizioneArtefatto, int dannoArma) {
        super(nome, descrizioneArtefatto);
        this.dannoArma = dannoArma;
    }

    public int getDannoArma() {
        return dannoArma;
    }

    public void equipaggiaA(Giocatore giocatore) {
        int danniPostEquipaggiamento = giocatore.getPuntiAttacco() + dannoArma;
        giocatore.setPuntiAttacco(danniPostEquipaggiamento);
    }
}