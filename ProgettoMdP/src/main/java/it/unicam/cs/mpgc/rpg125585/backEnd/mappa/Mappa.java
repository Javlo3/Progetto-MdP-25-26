package it.unicam.cs.mpgc.rpg125585.backEnd.mappa;

import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.StanzaGenerica;
import java.util.HashMap;
import java.util.Map;

public class Mappa {
    // La mappa tiene solo traccia delle stanze attive nel gioco
    private final Map<Integer, StanzaGenerica> stanze;
    private final int idStanzaIniziale;

    public Mappa() {
        this.stanze = new HashMap<>();
        this.idStanzaIniziale = 0;
    }

    public void aggiungiStanza(StanzaGenerica stanza) {
        this.stanze.put(stanza.getIdStanza(), stanza);
    }

    public StanzaGenerica getStanza(int idStanza) {
        return this.stanze.get(idStanza);
    }

    public int getIdStanzaIniziale() {
        return idStanzaIniziale;
    }
}