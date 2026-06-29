package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.Turno;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.EntitaGenerale;

public abstract class Giocatore extends EntitaGenerale{
    private Turno turno;

    public Giocatore(int puntiVita, int puntiAttacco, int puntiScudo, int distanzaAttacco) {
        super(puntiVita, puntiAttacco, puntiScudo, distanzaAttacco);
    }
}