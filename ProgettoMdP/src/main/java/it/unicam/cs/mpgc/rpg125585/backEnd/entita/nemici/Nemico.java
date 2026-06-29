package it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.EntitaGenerale;

public abstract class Nemico extends EntitaGenerale{

    public Nemico(int puntiVita, int puntiAttacco, int puntiDifesa, int puntiScudo, int distanzaAttacco) {
        super(puntiVita, puntiAttacco, puntiDifesa, puntiScudo, distanzaAttacco);
    }
    public abstract void attacco();
}