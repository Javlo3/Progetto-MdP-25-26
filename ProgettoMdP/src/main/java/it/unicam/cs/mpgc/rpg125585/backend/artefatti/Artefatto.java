package it.unicam.cs.mpgc.rpg125585.backend.artefatti;

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

}