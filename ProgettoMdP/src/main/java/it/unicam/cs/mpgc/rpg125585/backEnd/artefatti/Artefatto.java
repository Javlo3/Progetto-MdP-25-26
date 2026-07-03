package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

import it.unicam.cs.mpgc.rpg125585.dto.ArtefattoDTO;

public abstract class Artefatto {
    private final String nomeArtefatto;
    private final String descrizioneArtefatto;

    public Artefatto(String nomeArtefatto, String descrizioneArtefatto) {
        this.nomeArtefatto = nomeArtefatto;
        this.descrizioneArtefatto = descrizioneArtefatto;
    }

    public String getNomeArtefatto() {
        return nomeArtefatto;
    }

    public String getDescrizioneArtefatto() {
        return descrizioneArtefatto;
    }

    public static Artefatto creaDaDTO(ArtefattoDTO dto) {
        if ("Arma".equals(dto.getTipoArtefatto())) {
            return new Arma(dto.getNomeArtefatto(), dto.getDescrizioneArtefatto(), dto.getDannoArma());
        } else if ("Cura".equals(dto.getTipoArtefatto())) {
            return new Cura(dto.getNomeArtefatto(), dto.getDescrizioneArtefatto(), dto.getPuntiCura());
        }
        return null;
    }
}