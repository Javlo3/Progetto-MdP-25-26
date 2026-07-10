package it.unicam.cs.mpgc.rpg125585.backend.artefatti;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;

public class Cura extends Artefatto{
    private final int puntiCura;

    public Cura(String nomeArtefatto, String descrizioneArtefatto, int puntiCura){
        super(nomeArtefatto, descrizioneArtefatto);
        this.puntiCura = puntiCura;
    }

    public int getPuntiCura(){
        return puntiCura;
    }

    public void applicaEffetto(Giocatore giocatore) {
        int nuovaVita = Math.min(giocatore.getVitaMassima(), giocatore.getPuntiVita() + this.puntiCura);
        giocatore.setPuntiVita(nuovaVita);
        System.out.println(getNomeArtefatto() + " consumata! Vita attuale: " + giocatore.getPuntiVita());
    }
}