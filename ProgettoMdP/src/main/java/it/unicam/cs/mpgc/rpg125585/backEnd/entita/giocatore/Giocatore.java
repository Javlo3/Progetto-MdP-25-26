package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.Turno;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.EntitaGenerale;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

public abstract class Giocatore extends EntitaGenerale{
    private Turno turno;

    public Giocatore(int puntiVita, int puntiAttacco, int puntiDifesa, int puntiScudo, int distanzaAttacco) {
        super(puntiVita, puntiAttacco,  puntiDifesa, puntiScudo, distanzaAttacco);
    }
    public abstract void attacco(Nemico nemico);
}