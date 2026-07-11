package it.unicam.cs.mpgc.rpg125585.backend.convertitori;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.*;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaGenerica;
import it.unicam.cs.mpgc.rpg125585.backend.utils.GestoreFile;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import java.util.List;
import java.util.Map;

/**
 * Gestore principale della persistenza e del ciclo di vita della partita.
 * Si occupa di orchestrare il passaggio dei dati tra i file di salvataggio JSON,
 * i Data Transfer Object (DTO) e il modello di dominio logico.
 */
public class ConvertitorePartita {
    private final GestoreFile gestoreFile = new GestoreFile();
    private final ConvertitoreMappa convertitoreMappa = new ConvertitoreMappa();

    /**
     * Scenario 1: Nuova Partita.
     * Inizializza un nuovo contesto di gioco caricando la mappa base immodificabile dalle risorse,
     * istanzia l'eroe scelto e genera il primo salvataggio automatico su disco.
     *
     * @param classeScelta Il nome della classe del giocatore selezionata.
     * @param percorsoSalvataggio Il percorso in cui creare il file di salvataggio.
     * @return Lo stato logico locale della nuova partita appena creata.
     */
    public StatoGiocoLocale inizializzaNuovaPartita(String classeScelta, String percorsoSalvataggio) {
        // 1. Legge i DTO della mappa vergine immodificabile dalle risorse interne
        List<StanzaDTO> mappaBaseDto = gestoreFile.caricaMappaBase("mappa_base.json");

        if (mappaBaseDto == null) {
            throw new IllegalStateException("Impossibile caricare la mappa base dalle risorse");
        }

        // 2. Trasforma i DTO in stanze reali tramite il ConvertitoreMappa
        Map<Integer, StanzaGenerica> stanzeReali = convertitoreMappa.mappaDaDtoADominio(mappaBaseDto);

        // 3. Genera un eroe fresco di zecca tramite Factory polimorfica
        Giocatore eroeReale = istanziaNuovoEroe(classeScelta);

        // 4. Posiziona l'eroe nella stanza iniziale (ID 0)
        StanzaGenerica stanzaIniziale = stanzeReali.get(0);

        if (stanzaIniziale == null) {
            stanzaIniziale = stanzeReali.values().iterator().next();
        }
        eroeReale.setStanzaCorrente(stanzaIniziale);

        // 5. Esegue il primo salvataggio automatico immediato su disco
        SalvataggioDTO primoSalvataggio = creaSalvataggioDati(eroeReale, stanzeReali);
        gestoreFile.salvaPartita(percorsoSalvataggio, primoSalvataggio);

        return new StatoGiocoLocale(eroeReale, stanzeReali);
    }

    /**
     * Scenario 2: Carica Partita Esistente.
     * Ripristina lo stato completo di una sessione precedente leggendo i dati dal file JSON esterno.
     *
     * @param percorsoSalvataggio Il percorso del file di salvataggio da caricare.
     * @return Lo stato logico locale ricostruito e pronto all'uso.
     */
    public StatoGiocoLocale caricaPartitaEsistente(String percorsoSalvataggio) {
        SalvataggioDTO salvataggioDTO = gestoreFile.caricaPartitaSalvata(percorsoSalvataggio);

        if (salvataggioDTO == null) {
            throw new IllegalStateException("Nessun salvataggio trovato al percorso: " + percorsoSalvataggio);
        }

        // 1. Ricostruisce la mappa modificata basandosi sui flag booleani corretti
        Map<Integer, StanzaGenerica> stanzeReali = convertitoreMappa.mappaDaDtoADominio(salvataggioDTO.getMappaStanze());

        // 2. Ricostruisce l'eroe con le sue vecchie statistiche
        Giocatore eroeReale = ricostruisciEroeDaDto(salvataggioDTO.getGiocatore());

        // 3. Riposiziona l'eroe al suo posto usando l'accesso della mappa
        int idCorrente = salvataggioDTO.getIdStanzaCorrente();
        StanzaGenerica stanzaCorrente = stanzeReali.get(idCorrente);

        if (stanzaCorrente == null) {
            throw new IllegalStateException("Errore critico: Stanza corrente con ID: " + idCorrente + " non trovata");
        }
        eroeReale.setStanzaCorrente(stanzaCorrente);

        return new StatoGiocoLocale(eroeReale, stanzeReali);
    }

    /**
     * Scenario 3: Salvataggio in corso di gioco.
     * Serializza lo stato attuale del mondo di gioco e dell'eroe aggiornando il file JSON.
     *
     * @param percorsoSalvataggio Il percorso in cui sovrascrivere il salvataggio.
     * @param eroe L'istanza corrente del giocatore.
     * @param mappaCompleta La mappa attuale con lo stato aggiornato di tutte le stanze.
     */
    public void salvaPartitaInCorso(String percorsoSalvataggio, Giocatore eroe, Map<Integer, StanzaGenerica> mappaCompleta) {
        SalvataggioDTO salvataggioDTO = creaSalvataggioDati(eroe, mappaCompleta);
        gestoreFile.salvaPartita(percorsoSalvataggio, salvataggioDTO);
    }

    /**
     * Costruisce il DTO di salvataggio assicurandosi di mappare lo stato polimorfico
     * reale di ogni stanza, mantenendo intatti i relativi flag strutturali.
     */
    private SalvataggioDTO creaSalvataggioDati(Giocatore eroe, Map<Integer, StanzaGenerica> mappaCompleta) {
        GiocatoreDTO giocatoreDTO = new GiocatoreDTO(eroe);

        // Mappatura sicura sfruttando lo switch pattern matching interno al costruttore di StanzaDTO
        List<StanzaDTO> stanzeDTO = mappaCompleta.values().stream()
                .map(StanzaDTO::new)
                .toList();

        int idStanzaAttuale = eroe.getStanzaCorrente().getIdStanza();
        return new SalvataggioDTO(giocatoreDTO, stanzeDTO, idStanzaAttuale);
    }

    /**
     * Instanzia un nuovo eroe di livello iniziale in base alla stringa descrittiva fornita.
     */
    private Giocatore istanziaNuovoEroe(String classeScelta) {
        return switch (classeScelta) {
            case "Cavaliere" -> new Cavaliere(100, 100, 30, 3);
            case "Arciere" -> new Arciere(90, 90, 20, 2);
            case "Lanciere" -> new Lanciere(95, 95, 25, 2);
            default -> throw new IllegalArgumentException("Classe non valida: " + classeScelta);
        };
    }

    /**
     * Ricostruisce l'oggetto di dominio dell'eroe partendo dai dati precedentemente serializzati nel DTO.
     */
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