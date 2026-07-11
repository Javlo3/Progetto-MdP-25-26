package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Arma;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Cura;

// Rappresentazione leggera dell'artefatto per il trasferimento dati e la persistenza

public class ArtefattoDTO {

    private String tipoArtefatto;
    private String nomeArtefatto;
    private String descrizioneArtefatto;
    private int puntiCura;
    private int dannoArma;

    // Costruttore per la creazione e la deserializzazione GSON

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
        this.dannoArma = 0;
        this.puntiCura = 0;
        switch(artefatto) {
            case Arma arma -> this.dannoArma = arma.getDannoArma();
            case Cura cura -> this.puntiCura = cura.getPuntiCura();
            default -> throw new IllegalArgumentException("Tipo artefatto non supportato per la serializzazione: " +
                    artefatto.getClass());
        }
    }

    // Getter per la gestione dei dati nell'interfaccia grafica

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