package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;

public class Cura extends Artefatto{
    private int cura;

    public Cura(String nome, String descrizioneArtefatto, int cura){
        super(nome, descrizioneArtefatto);
        this.cura = cura;
    }

    public int getCura(){
        return cura;
    }

    public boolean usaCura(Giocatore bersaglio){
        if(bersaglio.getPuntiVita() < bersaglio.getVitaMassima()) {
            int vitaPostCura = bersaglio.getPuntiVita() + cura;
            if(vitaPostCura > bersaglio.getVitaMassima()) {
                vitaPostCura = bersaglio.getVitaMassima();
            }
            bersaglio.setPuntiVita(vitaPostCura);
            return true;
        }
        return false;
    }
}