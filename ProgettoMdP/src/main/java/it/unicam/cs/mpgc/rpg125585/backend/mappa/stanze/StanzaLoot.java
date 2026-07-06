package it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StanzaLoot extends StanzaGenerica {
    private final List<Artefatto> artefattiStanza;

    public StanzaLoot(int idStanza, String nomeStanza, String descrizioneStanza) {
        super(idStanza, nomeStanza, descrizioneStanza);
        this.artefattiStanza = new ArrayList<>();
    }

    public List<Artefatto> getArtefattiStanza() {
        return Collections.unmodifiableList(artefattiStanza);
    }

    public void aggiungiArtefatto(Artefatto artefatto) {
        if (artefatto != null) {
            this.artefattiStanza.add(artefatto);
        }
    }


    public Artefatto getArtefatto() {
        if(this.artefattiStanza == null ||this.artefattiStanza.isEmpty()){
            return null;
        }
            return this.artefattiStanza.getFirst();
    }
}