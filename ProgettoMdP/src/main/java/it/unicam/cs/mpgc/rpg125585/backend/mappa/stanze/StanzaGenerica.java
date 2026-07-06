package it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze;

public abstract class StanzaGenerica {
    private final int idStanza;
    private final String nomeStanza;
    private final String descrizioneStanza;
    private StanzaGenerica stanzaNord;
    private StanzaGenerica stanzaSud;
    private StanzaGenerica stanzaEst;
    private StanzaGenerica stanzaOvest;

    public StanzaGenerica(int idStanza, String nome, String descrizioneStanza) {
        this.idStanza = idStanza;
        this.nomeStanza = nome;
        this.descrizioneStanza = descrizioneStanza;
    }

    public int getIdStanza() {
        return idStanza;
    }

    public String getNomeStanza() {
        return nomeStanza;
    }

    public String getDescrizioneStanza() {
        return descrizioneStanza;
    }

    public StanzaGenerica getStanzaNord() {
        return stanzaNord;
    }

    public StanzaGenerica getStanzaSud() {
        return stanzaSud;
    }

    public StanzaGenerica getStanzaEst() {
        return stanzaEst;
    }

    public StanzaGenerica getStanzaOvest() {
        return stanzaOvest;
    }

    public void setCollegamenti(StanzaGenerica stanzaNord, StanzaGenerica stanzaSud, StanzaGenerica stanzaEst,
                                StanzaGenerica stanzaOvest) {
        this.stanzaNord = stanzaNord;
        this.stanzaSud = stanzaSud;
        this.stanzaEst = stanzaEst;
        this.stanzaOvest = stanzaOvest;
    }

}