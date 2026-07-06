package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Arma;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Cura;

public class ArtefattoDTO {

    private String tipoArtefatto;
    private String nomeArtefatto;
    private String descrizioneArtefatto;
    private int puntiCura;
    private int dannoArma;

    public ArtefattoDTO() {
    }

    public ArtefattoDTO(String tipoArtefatto, String nomeArtefatto, String descrizioneArtefatto, int puntiCura,
                        int dannoArma) {
        this.tipoArtefatto = tipoArtefatto;
        this.nomeArtefatto = nomeArtefatto;
        this.descrizioneArtefatto = descrizioneArtefatto;
        this.puntiCura = puntiCura;
        this.dannoArma = dannoArma;
    }

    public ArtefattoDTO(Artefatto artefatto) {
        this.tipoArtefatto = artefatto.getClass().getSimpleName();
        this.nomeArtefatto = artefatto.getNomeArtefatto();
        this.descrizioneArtefatto = artefatto.getDescrizioneArtefatto();
        if(artefatto instanceof Arma) {
            this.dannoArma = ((Arma) artefatto).getDannoArma();
            this.puntiCura = 0;
        }
        else {
            this.puntiCura = ((Cura) artefatto).getPuntiCura();
            this.dannoArma = 0;
        }
    }

    public String getTipoArtefatto() {
        return tipoArtefatto;
    }

    public String getNomeArtefatto() {
        return nomeArtefatto;
    }

    public String getDescrizioneArtefatto() {
        return descrizioneArtefatto;
    }

    public int getPuntiCura() {
        return puntiCura;
    }

    public int getDannoArma() {
        return dannoArma;
    }
}