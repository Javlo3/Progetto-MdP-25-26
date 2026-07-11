package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.*;

import java.util.List;

// Rappresentazione leggera della stanza per il trasferimento dati e la persistenza

public class StanzaDTO {

    private int idStanza;
    private String nomeStanza;
    private String descrizioneStanza;
    private List<NemicoDTO> nemiciNellaStanza;
    private List<ArtefattoDTO> artefattiNellaStanza;
    private int idStanzaNord, idStanzaSud, idStanzaEst, idStanzaOvest;
    private boolean isStanzaLoot;
    private boolean isStanzaCombattimento;

    // Costruttore per la creazione e la deserializzazione GSON

    public StanzaDTO() {}

    public StanzaDTO(int idStanza, String nomeStanza, String descrizioneStanza,List<NemicoDTO> nemiciNellaStanza,
                     List<ArtefattoDTO> artefattiNellaStanza, int idStanzaNord, int idStanzaSud,
                     int idStanzaEst, int idStanzaOvest) {
        this.idStanza = idStanza;
        this.nomeStanza = nomeStanza;
        this.descrizioneStanza = descrizioneStanza;
        this.nemiciNellaStanza = nemiciNellaStanza;
        this.artefattiNellaStanza = artefattiNellaStanza;
        this.idStanzaNord = idStanzaNord;
        this.idStanzaSud = idStanzaSud;
        this.idStanzaEst = idStanzaEst;
        this.idStanzaOvest = idStanzaOvest;
    }

    public StanzaDTO(StanzaGenerica stanza) {
        this.idStanza = stanza.getIdStanza();
        this.nomeStanza = stanza.getNomeStanza();
        this.descrizioneStanza = stanza.getDescrizioneStanza();
        this.isStanzaCombattimento = false;
        this.isStanzaLoot = false;
        switch (stanza) {
            case StanzaCombattimento sComb -> {
                this.isStanzaCombattimento = true;
                this.nemiciNellaStanza = sComb.getNemiciStanza().stream()
                        .map(NemicoDTO::new)
                        .toList();
            }
            case StanzaLoot sLoot -> {
                this.isStanzaLoot = true;
                if (sLoot.getArtefattiStanza() != null && !sLoot.getArtefattiStanza().isEmpty()) {
                    this.artefattiNellaStanza = sLoot.getArtefattiStanza().stream()
                            .map(ArtefattoDTO::new)
                            .toList();
                }
            }
            default -> {
                // Stanza normale/corridoio: non richiede dati aggiuntivi
            }
        }
        this.idStanzaNord = (stanza.getStanzaNord() != null ) ? stanza.getStanzaNord().getIdStanza() : -1;
        this.idStanzaSud = (stanza.getStanzaSud() != null ) ? stanza.getStanzaSud().getIdStanza() : -1;
        this.idStanzaEst = (stanza.getStanzaEst() != null ) ? stanza.getStanzaEst().getIdStanza() : -1;
        this.idStanzaOvest = (stanza.getStanzaOvest() != null ) ? stanza.getStanzaOvest().getIdStanza() : -1;
    }

    // Getter per la gestione dei dati nell'interfaccia grafica

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

    public int getIdStanzaNord() {
        return idStanzaNord;
    }

    public int getIdStanzaSud() {
        return idStanzaSud;
    }

    public int getIdStanzaEst() {
        return idStanzaEst;
    }

    public int getIdStanzaOvest() {
        return idStanzaOvest;
    }

    public String getDescrizioneStanza() {
        return descrizioneStanza;
    }

    public boolean isStanzaCombattimento() {
        return isStanzaCombattimento;
    }

    public boolean isStanzaLoot() {
        return isStanzaLoot;
    }
}