package it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno;

public class Turno {

    private TipoTurno turno;

    public Turno() {
        this.turno = TipoTurno.GIOCATORE;
    }

    public TipoTurno getTurno() {
        return turno;
    }

    public void cambiaTurno() {
        if (this.turno == TipoTurno.GIOCATORE) {
            this.turno = TipoTurno.NEMICO;
        } else {
            this.turno = TipoTurno.GIOCATORE;
        }
    }
}