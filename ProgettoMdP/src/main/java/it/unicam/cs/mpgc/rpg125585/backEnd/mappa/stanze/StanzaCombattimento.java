package it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StanzaCombattimento extends StanzaGenerica {

    private final List<Nemico> nemiciStanza;

    public StanzaCombattimento(int idStanza, String nomeStanza, String descrizioneStanza) {
        super(idStanza, nomeStanza, descrizioneStanza);
        this.nemiciStanza = new ArrayList<>();
    }

    public void aggiungiNemico(Nemico nemico) {
        if (nemico != null) {
            this.nemiciStanza.add(nemico);
        }
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