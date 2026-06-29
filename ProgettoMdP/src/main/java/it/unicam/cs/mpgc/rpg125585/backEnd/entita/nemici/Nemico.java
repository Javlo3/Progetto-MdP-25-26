package it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici;

public abstract class Nemico {
    private int puntiVita;
    private int puntiAttacco;
    private int puntiDifesa;
    private int puntiScudo;

    public Nemico(int puntiVita, int puntiAttacco, int puntiDifesa, int puntiScudo) {
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiDifesa = puntiDifesa;
        this.puntiScudo = puntiScudo;
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
