package it.unicam.cs.mpgc.rpg125585.backend.entita;

public abstract class EntitaGenerale {

    private final int vitaMassima;
    private final int puntiScudo;
    private int puntiVita;
    protected int puntiAttacco;

    public EntitaGenerale(int vitaMassima, int puntiVitaAttuali, int puntiAttacco, int puntiScudo) {
        this.vitaMassima = vitaMassima;
        this.puntiVita = puntiVitaAttuali;
        this.puntiAttacco = puntiAttacco;
        this.puntiScudo = puntiScudo;
    }

    public int getVitaMassima() {
        return vitaMassima;
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

    protected void setPuntiAttacco(int puntiAttacco) {
        this.puntiAttacco = puntiAttacco;
    }

    public int getPuntiScudo() {
        return puntiScudo;
    }

}