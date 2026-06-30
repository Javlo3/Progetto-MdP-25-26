package it.unicam.cs.mpgc.rpg125585.backEnd.artefatti;

public abstract class Artefatto {
    private String nome;
    private String descrizione;

    public Artefatto(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

}