package it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze;

public abstract class StanzaGenerica {
    private String nome;
    private String descrizione;

    public StanzaGenerica(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }
}