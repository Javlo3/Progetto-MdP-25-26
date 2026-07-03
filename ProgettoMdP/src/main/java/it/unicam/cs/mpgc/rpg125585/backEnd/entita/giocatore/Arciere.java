package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

import it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno.Turno;

public class Arciere extends Giocatore {

    private Turno turno;

    public Arciere(int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        super(vitaMassima, puntiVita, puntiAttacco,puntiScudo);
    }

}