package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;
import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.TipoTurno;
import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.Turno;

public class Arciere extends Giocatore {

    private Turno turno;

    public Arciere() {
        super(50, 50, 5, 2, 3);
    }

    public void attacco(Nemico nemico) {
        if (turno.getTurno() == TipoTurno.GIOCATORE) {
        }
    }
}