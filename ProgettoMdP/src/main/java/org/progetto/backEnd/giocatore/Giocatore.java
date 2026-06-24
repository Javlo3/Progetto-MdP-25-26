package org.progetto.backEnd.giocatore;

public class Giocatore {
    private int puntiVita;
    private int puntiAttacco;
    private int puntiDifesa;
    private int puntiScudo;

    public Giocatore(ClasseDiAppartenenza classeDiAppartenenza) {
        switch(classeDiAppartenenza){
            case ARCIERE -> {
                this.setPuntiVita(0);
                this.setPuntiAttacco(0);
                this.setPuntiDifesa(0);
                this.setPuntiScudo(0);
            }
            case LANCIERE -> {
                this.setPuntiVita(4);
                this.setPuntiAttacco(0);
                this.setPuntiDifesa(0);
                this.setPuntiScudo(0);
            }
            case CAVALIERE -> {
                this.setPuntiVita(7);
                this.setPuntiAttacco(0);
                this.setPuntiDifesa(0);
                this.setPuntiScudo(0);
            }
            default -> {throw new IllegalArgumentException("Classe sconosciuta");}
        }
    }

    public int getPuntiVita() {
        return puntiVita;
    }
    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }
    public int getPuntiAttacco() {
        return puntiAttacco;
    }
    public void setPuntiAttacco(int puntiAttacco) {
        this.puntiAttacco = puntiAttacco;
    }
    public int getPuntiDifesa() {
        return puntiDifesa;
    }
    public void setPuntiDifesa(int puntiDifesa) {
        this.puntiDifesa = puntiDifesa;
    }
    public int getPuntiScudo() {
        return puntiScudo;
    }
    public void setPuntiScudo(int puntiScudo) {
        this.puntiScudo = puntiScudo;
    }

}
