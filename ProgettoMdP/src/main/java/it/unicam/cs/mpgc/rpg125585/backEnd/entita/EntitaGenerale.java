package it.unicam.cs.mpgc.rpg125585.backEnd.entita;

public abstract class EntitaGenerale {

    private int puntiVita;
    private int puntiAttacco;
    private int puntiScudo;
    private int distanzaAttacco;

    public EntitaGenerale(int puntiVita, int puntiAttacco, int puntiScudo, int distanzaAttacco) {
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiScudo = puntiScudo;
        this.distanzaAttacco = distanzaAttacco;
    }

    public void dannoRicevuto(int danno) {
        int dannoRealeRicevuto = danno - this.puntiScudo;
        if(dannoRealeRicevuto < 0) dannoRealeRicevuto = 0;
        this.puntiVita -= dannoRealeRicevuto;
    }

    public boolean attacco(EntitaGenerale bersaglio, int distanzaAttuale) {
        if(distanzaAttuale <= this.getDistanzaAttacco() ) {
            bersaglio.dannoRicevuto(this.getPuntiAttacco());
            return true;
        }
        return false;
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