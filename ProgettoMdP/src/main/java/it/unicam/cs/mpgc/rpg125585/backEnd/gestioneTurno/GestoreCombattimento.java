package it.unicam.cs.mpgc.rpg125585.backEnd.gestioneTurno;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

public class GestoreCombattimento {
    private Nemico nemico;
    private Giocatore giocatore;
    private int distanzaDeiPersonaggi;

    public GestoreCombattimento(Nemico nemico, Giocatore giocatore, int distanzaIniziale) {
        this.nemico = nemico;
        this.giocatore = giocatore;
        this.distanzaDeiPersonaggi = distanzaIniziale;
    }

    public boolean attaccoNemico() {
        if(this.distanzaDeiPersonaggi <= nemico.getDistanzaAttacco()) {
            giocatore.dannoRicevuto(nemico.getPuntiAttacco());
            return true;
        }
        return false;
    }

    public boolean attaccoGiocatore() {
        if(this.distanzaDeiPersonaggi <= giocatore.getDistanzaAttacco()) {
            nemico.dannoRicevuto(giocatore.getPuntiAttacco());
            return true;
        }
        return false;
    }

    public void avvicinamento() {
        if (distanzaDeiPersonaggi > 1) distanzaDeiPersonaggi--;
    }

    public void allontana() {
        distanzaDeiPersonaggi++;
    }

    public int getDistanzaDeiPersonaggi() {return distanzaDeiPersonaggi;}
}