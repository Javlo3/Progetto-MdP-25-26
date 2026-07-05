package it.unicam.cs.mpgc.rpg125585.backEnd.convertitori;

import it.unicam.cs.mpgc.rpg125585.backEnd.artefatti.*;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.*;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.goblin.*;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.stregoni.*;
import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.*;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertitoreMappa {

    public Map<Integer, StanzaGenerica> mappaDaDtoADominio(List<StanzaDTO> stanzeDTO) {
        Map<Integer, StanzaGenerica> mappaStanze = new HashMap<>();
        for (StanzaDTO stanzaSingolaDTO : stanzeDTO) {
            mappaStanze.put(stanzaSingolaDTO.getIdStanza(), creaStanzaSingolaDaDto(stanzaSingolaDTO));
        }
        for (StanzaDTO stanzaSingolaDTO : stanzeDTO) {
            StanzaGenerica corrente = mappaStanze.get(stanzaSingolaDTO.getIdStanza());
            corrente.setCollegamenti(mappaStanze.get(stanzaSingolaDTO.getIdStanzaNord()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaSud()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaEst()),
                    mappaStanze.get(stanzaSingolaDTO.getIdStanzaOvest())
            );
        }
        return Map.copyOf(mappaStanze);
    }

    public List<StanzaDTO> mappaADominioDto(Map<Integer, StanzaGenerica> mappaCompleta) {
        return mappaCompleta.values()
                .stream()
                .map(StanzaDTO::new)
                .toList();
    }

    private StanzaGenerica creaStanzaSingolaDaDto(StanzaDTO stanza) {
        if(haNemici(stanza)) {
            return costruisciStanzaCombattimento(stanza);
        }
        if(haArtefatti(stanza)) {
            return costruisciStanzaLoot(stanza);
        }
        return new StanzaCorridoio(stanza.getIdStanza(), stanza.getNomeStanza(), stanza.getDescrizioneStanza());
    }

    private boolean haNemici(StanzaDTO stanzaDto) {
        return stanzaDto.getNemiciNellaStanza() != null && !stanzaDto.getNemiciNellaStanza().isEmpty();
    }

    private boolean haArtefatti(StanzaDTO stanzaDto) {
        return stanzaDto.getArtefattiNellaStanza() != null && !stanzaDto.getArtefattiNellaStanza().isEmpty();
    }

    private StanzaCombattimento costruisciStanzaCombattimento(StanzaDTO stanzaDto) {
        StanzaCombattimento stanzaCombat = new StanzaCombattimento(stanzaDto.getIdStanza(), stanzaDto.getNomeStanza(),
                stanzaDto.getDescrizioneStanza());
        stanzaDto.getNemiciNellaStanza().forEach(nemicoDTO -> {
            Nemico nemicoReale = switch (nemicoDTO.getTipoNemico()) {
                case "GoblinSemplice" -> new GoblinSemplice(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                        nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                case "GoblinLanciere" -> new GoblinLanciere(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                        nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                case "Stregone" -> new Stregone(nemicoDTO.getNomeNemico(), nemicoDTO.getVitaMassima(),
                        nemicoDTO.getPuntiVita(), nemicoDTO.getPuntiAttacco(), nemicoDTO.getPuntiScudo());
                default -> throw new IllegalArgumentException("Tipo nemico sconosciuto");
            };
            stanzaCombat.aggiungiNemico(nemicoReale);
        });
        return stanzaCombat;
    }

    private StanzaLoot costruisciStanzaLoot(StanzaDTO stanzaDto) {
        StanzaLoot stanzaLo = new StanzaLoot(stanzaDto.getIdStanza(), stanzaDto.getNomeStanza(),
                stanzaDto.getDescrizioneStanza());
        stanzaDto.getArtefattiNellaStanza().forEach(arteDto -> {
            Artefatto arteReale = switch (arteDto.getTipoArtefatto()) {
                case "Cura" -> new Cura(arteDto.getNomeArtefatto(), arteDto.getDescrizioneArtefatto(),
                        arteDto.getPuntiCura());
                case "Arma" -> new Arma(arteDto.getNomeArtefatto(), arteDto.getDescrizioneArtefatto(),
                        arteDto.getDannoArma()
                );
                default -> throw new IllegalArgumentException("Tipo artefatto sconosciuto");
            };
            stanzaLo.aggiungiArtefatto(arteReale);
        });
        return stanzaLo;
    }
}