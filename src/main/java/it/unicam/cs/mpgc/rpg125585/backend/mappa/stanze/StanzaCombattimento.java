package it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze;

import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.Nemico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StanzaCombattimento extends StanzaGenerica {

    private final List<Nemico> nemiciStanza;

    public StanzaCombattimento(int idStanza, String nomeStanza, String descrizioneStanza) {
        super(idStanza, nomeStanza, descrizioneStanza);
        this.nemiciStanza = new ArrayList<>();
    }

    public List<Nemico> getNemiciStanza() {
        return Collections.unmodifiableList(nemiciStanza);
    }

    public void aggiungiNemico(Nemico nemico) {
        if (nemico != null) {
            this.nemiciStanza.add(nemico);
        }
    }

}