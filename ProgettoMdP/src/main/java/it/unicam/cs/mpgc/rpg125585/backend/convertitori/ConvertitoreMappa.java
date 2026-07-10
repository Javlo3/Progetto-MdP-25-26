package it.unicam.cs.mpgc.rpg125585.backend.convertitori;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.*;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.*;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.goblin.*;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.stregoni.*;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.*;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertitoreMappa {

    public Map<Integer, StanzaGenerica> mappaDaDtoADominio(List<StanzaDTO> stanzeDTO) {
        Map<Integer, StanzaGenerica> mappaStanze = new HashMap<>();
        for (StanzaDTO stanzaSingolaDTO : stanzeDTO)
            mappaStanze.put(stanzaSingolaDTO.getIdStanza(), creaStanzaSingolaDaDto(stanzaSingolaDTO));
        for(
    StanzaDTO stanzaSingolaDTO :stanzeDTO)
    {
        StanzaGenerica corrente = mappaStanze.get(stanzaSingolaDTO.getIdStanza());
        if (corrente != null) {
            corrente.setCollegamenti(mappaStanze.get(stanzaSingolaDTO.getIdStanzaNord()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaSud()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaEst()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaOvest())
            );
        }
    }
        return Map.copyOf(mappaStanze);
    }
    
    /**
     * Determina l'istanza corretta basandosi sui flag strutturali immutabili del DTO,
     * impedendo che una stanza ripulita da nemici/loot si declassi erroneamente a StanzaCorridoio.
     */
    private StanzaGenerica creaStanzaSingolaDaDto(StanzaDTO stanza) {
        if (stanza.isStanzaCombattimento()) {
            return costruisciStanzaCombattimento(stanza);
        }
        if (stanza.isStanzaLoot()) {
            return costruisciStanzaLoot(stanza);
        }
        return new StanzaCorridoio(stanza.getIdStanza(), stanza.getNomeStanza(), stanza.getDescrizioneStanza());
    }

    private StanzaCombattimento costruisciStanzaCombattimento(StanzaDTO stanzaDto) {
        StanzaCombattimento stanzaCombat = new StanzaCombattimento(stanzaDto.getIdStanza(), stanzaDto.getNomeStanza(),
                stanzaDto.getDescrizioneStanza());
        // Popola i nemici solo se presenti nel salvataggio (se la stanza è già stata ripulita, la lista sarà vuota)
        if (stanzaDto.getNemiciNellaStanza() != null) {
            stanzaDto.getNemiciNellaStanza().forEach(nemicoDTO -> {
                Nemico nemicoReale = switch (nemicoDTO.getTipoNemico()) {
                    case "GoblinSemplice" -> new GoblinSemplice(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                            nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                    case "GoblinLanciere" -> new GoblinLanciere(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                            nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                    case "Stregone" -> new Stregone(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                            nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                    default -> throw new IllegalArgumentException("Tipo nemico sconosciuto: " + nemicoDTO.getTipoNemico());
                };
                stanzaCombat.aggiungiNemico(nemicoReale);
            });
        }
        return stanzaCombat;
    }

    private StanzaLoot costruisciStanzaLoot(StanzaDTO stanzaDto) {
        StanzaLoot stanzaLo = new StanzaLoot(stanzaDto.getIdStanza(), stanzaDto.getNomeStanza(),
                stanzaDto.getDescrizioneStanza());
        // Popola l'artefatto solo se non ancora raccolto (evita anomalie post-raccolta)
        if (stanzaDto.getArtefattiNellaStanza() != null) {
            stanzaDto.getArtefattiNellaStanza().forEach(arteDto -> {
                Artefatto arteReale = switch (arteDto.getTipoArtefatto()) {
                    case "Cura" -> new Cura(arteDto.getNomeArtefatto(), arteDto.getDescrizioneArtefatto(),
                            arteDto.getPuntiCura());
                    case "Arma" -> new Arma(arteDto.getNomeArtefatto(), arteDto.getDescrizioneArtefatto(),
                            arteDto.getDannoArma()
                    );
                    default -> throw new IllegalArgumentException("Tipo artefatto sconosciuto: " + arteDto.getTipoArtefatto());
                };
                stanzaLo.aggiungiArtefatto(arteReale);
            });
        }
        return stanzaLo;
    }
}