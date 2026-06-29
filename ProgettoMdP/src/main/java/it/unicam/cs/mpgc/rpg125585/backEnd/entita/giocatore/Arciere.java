package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.Turno;

public class Arciere extends Giocatore {

    private Turno turno;

    public Arciere() {
        super(50, 15, 5, 3);
    }

    public Arciere(int puntiVitaSalvataggio, int puntiAttaccoSalvataggio, int puntiScudoSalvataggio,
                    int distanzaAttaccoSalvataggio) {
        super(puntiVitaSalvataggio, puntiAttaccoSalvataggio, puntiScudoSalvataggio, distanzaAttaccoSalvataggio);
    }
}