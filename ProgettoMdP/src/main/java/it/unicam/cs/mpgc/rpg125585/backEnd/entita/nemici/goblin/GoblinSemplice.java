package it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.goblin;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

public class GoblinSemplice extends Nemico {

    public GoblinSemplice(int puntiVita, int puntiAttacco, int puntiDifesa, int puntiScudo) {
        super(puntiVita, puntiAttacco, puntiDifesa,  puntiScudo, 0);
    }

    public void attacco() {}
}
