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

    public boolean usaCura(Giocatore bersaglio) {
        if (bersaglio.getPuntiVita() < bersaglio.getVitaMassima()) {
            bersaglio.curaRicevuta(this.puntiCura); // Diciamo all'oggetto cosa fare!
            return true;
        }
        return false;
    }
}