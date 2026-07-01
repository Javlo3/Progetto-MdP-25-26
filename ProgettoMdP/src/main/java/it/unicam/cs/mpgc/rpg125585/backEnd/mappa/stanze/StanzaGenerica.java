package it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze;

public abstract class StanzaGenerica {
    private final String nome;
    private final String descrizione;
    private StanzaGenerica stanzaNord;
    private StanzaGenerica stanzaSud;
    private StanzaGenerica stanzaEst;
    private StanzaGenerica stanzaOvest;

    public StanzaGenerica(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public StanzaGenerica getStanzaNord() {
        return stanzaNord;
    }

    public void setStanzaNord(StanzaGenerica stanzaNord) {
        this.stanzaNord = stanzaNord;
    }

    public StanzaGenerica getStanzaSud() {
        return stanzaSud;
    }

    public void setStanzaSud(StanzaGenerica stanzaSud) {
        this.stanzaSud = stanzaSud;
    }

    public StanzaGenerica getStanzaEst() {
        return stanzaEst;
    }

    public void setStanzaEst(StanzaGenerica stanzaEst) {
        this.stanzaEst = stanzaEst;
    }

    public StanzaGenerica getStanzaOvest() {
        return stanzaOvest;
    }

    public void setStanzaOvest(StanzaGenerica stanzaOvest) {
        this.stanzaOvest = stanzaOvest;
    }
}