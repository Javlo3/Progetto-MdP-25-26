package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

public abstract class Artefatto {
    private final String nome;
    private final String descrizioneArtefatto;

    public Artefatto(String nome, String descrizioneArtefatto) {
        this.nome = nome;
        this.descrizioneArtefatto = descrizioneArtefatto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizioneArtefatto() {
        return descrizioneArtefatto;
    }
}