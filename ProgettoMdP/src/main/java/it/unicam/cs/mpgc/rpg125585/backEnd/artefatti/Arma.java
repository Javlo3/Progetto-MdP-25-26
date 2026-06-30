package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;

public class Arma extends Artefatto{

    private int dannoArma;

    public Arma(String nome, String descrizione, int dannoArma) {
        super(nome, descrizione);
        this.dannoArma = dannoArma;
    }

    public int getDannoArma() {
        return dannoArma;
    }

    public void setDannoGiocatore(Giocatore bersaglio){
        int danniPostEquipaggiamento = bersaglio.getPuntiAttacco() + dannoArma;
        bersaglio.setPuntiAttacco(danniPostEquipaggiamento);
    }
}
