package it.unicam.cs.mpgc.rpg125585.backEnd.convertitori;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.*;
import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.StanzaGenerica;
import it.unicam.cs.mpgc.rpg125585.backEnd.utils.GestoreFile;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import java.util.List;
import java.util.Map;

public class ConvertitorePartita {
    private final GestoreFile gestoreFile = new GestoreFile();
    private final ConvertitoreMappa convertitoreMappa = new ConvertitoreMappa();

    /**

     * SCENARIO 1: Nuova Partita (Mappa Base dalle risorse + Salvataggio Automatico)

     */
    public StatoGiocoLocale inizializzaNuovaPartita(String classeScelta, String percorsoSalvataggio) {
        // 1. Legge i DTO della mappa vergine immodificabile dalle risorse interne
        List<StanzaDTO> mappaBaseDto = gestoreFile.caricaMappaBase("mappabase.json");
        if(mappaBaseDto == null){
            throw new IllegalStateException("Impossibile caricare la mappa base dalle risorse");
        }
        // 2. Trasforma i DTO in stanze reali tramite il ConvertitoreMappa
        Map<Integer, StanzaGenerica> stanzeReali = convertitoreMappa.mappaDaDtoADominio(mappaBaseDto);
        // 3. Genera un eroe fresco di zecca tramite Factory polimorfica
        Giocatore eroeReale = istanziaNuovoEroe(classeScelta);
        // 4. Posiziona l'eroe nella stanza iniziale (assumiamo ID 1)
        StanzaGenerica stanzaIniziale = stanzeReali.get(1);
        if(stanzaIniziale == null){
            stanzaIniziale = stanzeReali.values().iterator().next();
        }
        eroeReale.setStanzaCorrente(stanzaIniziale);
        // 5. ESEGUE IL PRIMO SALVATAGGIO AUTOMATICO IMMEDIATO SU DISCO
        SalvataggioDTO primoSalvataggio = creaSalvataggioDati(eroeReale, stanzeReali);
        gestoreFile.salvaPartita(percorsoSalvataggio, primoSalvataggio);
        return new StatoGiocoLocale(eroeReale, stanzeReali);
    }

    /**
     * SCENARIO 2: Carica Partita Esistente (Legge TUTTO dal file esterno)
     */
    public StatoGiocoLocale caricaPartitaEsistente(String percorsoSalvataggio) {
        SalvataggioDTO salvataggioDTO = gestoreFile.caricaPartitaSalvata(percorsoSalvataggio);
        if (salvataggioDTO == null) {
            throw new IllegalStateException("Nessun salvataggio trovato al percorso: " + percorsoSalvataggio);
        }
        // 1. Ricostruisce la mappa modificata
        Map<Integer, StanzaGenerica> stanzeReali = convertitoreMappa.mappaDaDtoADominio(salvataggioDTO.getMappaStanze());
        // 2. Ricostruisce l'eroe con le sue vecchie statistiche
        Giocatore eroeReale = ricostruisciEroeDaDto(salvataggioDTO.getGiocatore());
        // 3. Riposiziona l'eroe al suo posto usando l'accesso della mappa
        int idCorrente = salvataggioDTO.getIdStanzaCorrente();
        StanzaGenerica stanzaCorrente = stanzeReali.get(idCorrente);
        if(stanzaCorrente == null){
            throw new IllegalStateException("Errore critico: Stanza corrente con ID: " + idCorrente + " non trovata");
        }
        eroeReale.setStanzaCorrente(stanzaCorrente);
        return new StatoGiocoLocale(eroeReale, stanzeReali);
    }

    /**
     * SCENARIO 3: Salvataggio in corso di gioco (Aggiorna il file esistente)
     */
    public void salvaPartitaInCorso(String percorsoSalvataggio, Giocatore eroe, Map<Integer, StanzaGenerica> mappaCompleta) {
        SalvataggioDTO salvataggioDTO = creaSalvataggioDati(eroe, mappaCompleta);
        gestoreFile.salvaPartita(percorsoSalvataggio, salvataggioDTO);
    }

    private SalvataggioDTO creaSalvataggioDati(Giocatore eroe, Map<Integer, StanzaGenerica> mappaCompleta) {
        GiocatoreDTO giocatoreDTO = new GiocatoreDTO(eroe);
        List<StanzaDTO> stanzeDTO = convertitoreMappa.mappaADominioDto(mappaCompleta);
        int idStanzaAttuale = eroe.getStanzaCorrente().getIdStanza();
        return new SalvataggioDTO(giocatoreDTO, stanzeDTO, idStanzaAttuale);
    }

    private Giocatore istanziaNuovoEroe(String classeScelta) {
        return switch (classeScelta) {
            case "Cavaliere" -> new Cavaliere(70, 70, 15, 3);
            case "Arciere" -> new Arciere(50, 50, 15, 1);
            case "Lanciere" -> new Lanciere(55, 50, 15, 2);
            default -> throw new IllegalArgumentException("Classe non valida: " + classeScelta);

        };

    }
    private Giocatore ricostruisciEroeDaDto(GiocatoreDTO dto) {
        return switch (dto.getTipoClasse()) {
            case "Cavaliere" -> new Cavaliere(dto.getVitaMassima(), dto.getPuntiVita(), dto.getPuntiAttacco(),
                    dto.getPuntiScudo());
            case "Arciere" -> new Arciere(dto.getVitaMassima(), dto.getPuntiVita(), dto.getPuntiAttacco(),
                    dto.getPuntiScudo());
            case "Lanciere" -> new Lanciere(dto.getVitaMassima(), dto.getPuntiVita(), dto.getPuntiAttacco(),
                    dto.getPuntiScudo());
            default -> throw new IllegalArgumentException("Classe sconosciuta nel JSON: " + dto.getTipoClasse());
        };
    }
}