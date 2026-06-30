package it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze;

import it.unicam.cs.mpgc.rpg125585.backEnd.artefatti.Artefatto;

public class StanzaLoot extends StanzaGenerica {
    private Artefatto artefatto;

    public StanzaLoot(String nome, String descrizione, Artefatto artefatto) {
        super(nome, descrizione);
        this.artefatto = artefatto;
    }

    public Artefatto getArtefatto() {
        return artefatto;
    }

    public void setArtefatto(Artefatto artefatto) {
        this.artefatto = artefatto;
    }
}