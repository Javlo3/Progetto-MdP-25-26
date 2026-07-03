package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.*;

import java.util.Collections;
import java.util.List;

public class StanzaDTO {

    private int idStanza;
    private String nomeStanza;
    private List<NemicoDTO> nemiciNellaStanza;
    private List<ArtefattoDTO> artefattiNellaStanza;

    public StanzaDTO() {
    }

    public StanzaDTO(int idStanza, String nomeStanza, List<NemicoDTO> nemiciNellaStanza, List<ArtefattoDTO> artefattiNellaStanza) {
        this.idStanza = idStanza;
        this.nomeStanza = nomeStanza;
        this.nemiciNellaStanza = nemiciNellaStanza;
        this.artefattiNellaStanza = artefattiNellaStanza;
    }

    public StanzaDTO(StanzaGenerica stanza) {
        this.idStanza = stanza.getIdStanza();
        this.nomeStanza = stanza.getNomeStanza();
        if (stanza instanceof StanzaCombattimento sComb) {
            this.nemiciNellaStanza = sComb.getNemiciStanza().stream()
                    .map(NemicoDTO::new)
                    .toList();
        }
        if (stanza instanceof StanzaLoot sLoot) {
            this.artefattiNellaStanza = Collections.singletonList(new ArtefattoDTO(sLoot.getArtefatto()));
        }
    }

    public int getIdStanza() {
        return idStanza;
    }

    public String getNomeStanza() {
        return nomeStanza;
    }

    public List<NemicoDTO> getNemiciNellaStanza() {
        return nemiciNellaStanza;
    }

    public List<ArtefattoDTO> getArtefattiNellaStanza() {
        return artefattiNellaStanza;
    }
}