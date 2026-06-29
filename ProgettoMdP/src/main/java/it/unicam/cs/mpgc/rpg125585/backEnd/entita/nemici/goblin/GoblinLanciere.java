package it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.goblin;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

public class GoblinLanciere extends Nemico {

    public GoblinLanciere(int puntiVita, int  puntiAttacco, int puntiDifesa, int puntiScudo) {
        super(puntiVita, puntiAttacco, puntiDifesa, puntiScudo, 10);
    }

    public void attacco() {}
}