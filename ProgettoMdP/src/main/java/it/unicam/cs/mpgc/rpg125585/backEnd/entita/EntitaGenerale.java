package it.unicam.cs.mpgc.rpg125585.backEnd.entita;

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

    //Lo scudo non scende mai, protegge sempre il giocatore senza mai rompersi
    public void dannoRicevuto(int danno) {
        int dannoRealeRicevuto = danno - this.puntiScudo;
        if (dannoRealeRicevuto < 0) dannoRealeRicevuto = 0;
        this.puntiVita -= dannoRealeRicevuto;
        if (this.puntiVita < 0) this.puntiVita = 0;
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

    /**
     * Incrementa i punti vita attuali senza mai superare la vita massima.
     */
    public void curaRicevuta(int puntiCura) {
        if (puntiCura < 0) return; // Protezione da valori assurdi

        this.puntiVita += puntiCura;
        if (this.puntiVita > this.vitaMassima) {
            this.puntiVita = this.vitaMassima;
        }
    }
}