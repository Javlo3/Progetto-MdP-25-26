package it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

import java.util.Collections;
import java.util.List;

public class StanzaCombattimento extends StanzaGenerica {

    private final List<Nemico> nemiciStanza;

    public StanzaCombattimento(String nome, String descrizioneStanza, List<Nemico> nemiciStanza) {
        super(nome, descrizioneStanza);
        this.nemiciStanza = nemiciStanza;
    }

    public List<Nemico> getNemiciStanza() {
        return Collections.unmodifiableList(nemiciStanza);
    }

    public boolean haNemiciVivi() {
        for (Nemico nemico : nemiciStanza) {
            if(nemico.getPuntiVita() > 0 ) {
                return true;
            }
        }
        return false;
    }
}