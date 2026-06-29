package it.unicam.cs.mpgc.rpg125585.backEnd.entita;

public abstract class EntitaGenerale {
    private int puntiVita;
    private int puntiAttacco;
    private int puntiDifesa;
    private int puntiScudo;
    private int distanzaAttacco;

    public EntitaGenerale(int puntiVita, int puntiAttacco, int puntiDifesa, int puntiScudo, int distanzaAttacco) {
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiDifesa = puntiDifesa;
        this.puntiScudo = puntiScudo;
        this.distanzaAttacco = distanzaAttacco;
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

    public int getDistanzaAttacco() {
        return distanzaAttacco;
    }

    public void setDistanzaAttacco(int distanzaAttacco) {
        this.distanzaAttacco = distanzaAttacco;
    }
}
