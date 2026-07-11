package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.backend.artefatti.Artefatto;
import it.unicam.cs.mpgc.rpg125585.backend.convertitori.StatoGiocoLocale;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.Nemico;
import it.unicam.cs.mpgc.rpg125585.backend.gestionecombattimenti.GestoreCombattimento;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaCombattimento;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaGenerica;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaLoot;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GiocoController {

    @FXML private Label lblNomeStanza, lblDescrizioneStanza, lblNomeArtefatto, lblDescrizioneArtefatto;
    @FXML private Label lblGiocatore, lblVitaGiocatore, lblScudoGiocatore, lblAttaccoGiocatore;
    @FXML private Label lblNomeNemico, lblVitaNemico, lblScudoNemico, lblAttaccoNemico;
    @FXML private Button btnStanzaNord, btnStanzaSud, btnStanzaOvest, btnStanzaEst, btnPrendiArtefatto;

    private SalvataggioDTO statoPartita;
    private int indiceNemicoTarget;
    private GestoreCombattimento gestoreCombattimento;
    private StatoGiocoLocale statoLogicoBackend;

    // Handler FXML, gestione dei click sui pulsanti della GUI

    @FXML
    public void handleStanzaNord() {
        if (isCombattimentoAttivo()) {
            gestisciTurnoCombattimento();
        } else {
            gestisciSpostamentoEsplorazione("Nord");
        }
    }

    @FXML
    public void handleStanzaSud() {
        if (isCombattimentoAttivo()) {
            System.out.println("Non puoi fuggire! Ci sono nemici vivi.");
        } else {
            gestisciSpostamentoEsplorazione("Sud");
        }
    }

    @FXML
    public void handleStanzaEst() {
        if (isCombattimentoAttivo()) {
            incrementaTargetNemico();
            aggiornaGrafica();
        } else {
            gestisciSpostamentoEsplorazione("Est");
        }
    }

    @FXML
    public void handleStanzaOvest() {
        if (isCombattimentoAttivo()) {
            StanzaDTO stanza = trovaStanzaCorrente();

            if (stanza != null) {
                // Filtriamo solo i nemici vivi per il decremento del target
                List<NemicoDTO> vivi = stanza
                        .getNemiciNellaStanza()
                        .stream()
                        .filter(n -> n.getPuntiVita() > 0)
                        .toList();
                decrementaTargetNemico(vivi);
                aggiornaGrafica();
            }
        } else {
            gestisciSpostamentoEsplorazione("Ovest");
        }
    }

    @FXML
    public void handlePrendiArtefatto() {
        StanzaDTO stanzaCorrente = trovaStanzaCorrente();

        if (stanzaCorrente != null && stanzaCorrente.isStanzaLoot()) {
            StanzaGenerica stanzaRealeGenerica = this.statoLogicoBackend.mappaStanze().get(stanzaCorrente.getIdStanza());

            if (stanzaRealeGenerica instanceof StanzaLoot stanzaReale) {
                Artefatto artefatto = stanzaReale.getArtefatto();

                if (artefatto != null) {
                    artefatto.applicaEffetto(this.statoLogicoBackend.giocatore());
                    stanzaReale.rimuoviPrimoArtefatto();
                    salvaESincronizzaTutto();
                    aggiornaGrafica();
                }
            }
        }
    }

    // Setup e metodi utili per lo stato

    public void inizializzaInterfaccia(SalvataggioDTO partita, StatoGiocoLocale statoReale) {
        this.statoPartita = partita;
        this.statoLogicoBackend = statoReale;
        this.indiceNemicoTarget = 0;
        aggiornaGrafica();
    }

    private boolean isCombattimentoAttivo() {
        StanzaDTO stanza = trovaStanzaCorrente();
        return stanza != null && stanza.isStanzaCombattimento() && gestoreCombattimento != null && gestoreCombattimento.ciSonoNemiciVivi();
    }

    private StanzaDTO trovaStanzaCorrente() {
        int idCercato = statoPartita.getIdStanzaCorrente();

        for (StanzaDTO stanza : statoPartita.getMappaStanze()) {
            if (stanza.getIdStanza() == idCercato) return stanza;
        }

        return null;
    }

    // Logica e flusso del sistema di combattimento

    private void gestisciTurnoCombattimento() {
        StanzaDTO stanzaCorrente = trovaStanzaCorrente();

        if (stanzaCorrente == null) return;
        StanzaCombattimento stanzaCombatReale = (StanzaCombattimento) this.statoLogicoBackend.mappaStanze()
                .get(stanzaCorrente.getIdStanza());

        if (stanzaCombatReale.getNemiciStanza().isEmpty()) {
            concludiCombattimento();
            return;
        }

        configuraBersaglioCombattimento(stanzaCombatReale);
        if (!gestoreCombattimento.eseguiTurnoAttacco()) return;

        gestioneMorteGiocatore();
        if (isStanzaSenzaNemici(stanzaCombatReale)) {
            concludiCombattimento();
            return;
        }

        salvaESincronizzaTutto();
        aggiornaGrafica();
    }

    private void configuraBersaglioCombattimento(StanzaCombattimento stanzaCombatReale) {
        // Prendiamo solo i nemici effettivamente vivi dal backend reale
        List<Nemico> nemiciViviReali = stanzaCombatReale
                .getNemiciStanza()
                .stream()
                .filter(n -> n.getPuntiVita() > 0)
                .toList();

        if (nemiciViviReali.isEmpty()) return;

        if (indiceNemicoTarget >= nemiciViviReali.size()) {
            indiceNemicoTarget = 0;
        }

        Nemico nemicoDaColpire = nemiciViviReali.get(indiceNemicoTarget);
        gestoreCombattimento.cambiaBersaglio(nemicoDaColpire);
    }

    private void gestioneMorteGiocatore() {
        if (this.statoLogicoBackend.giocatore().getPuntiVita() <= 0) {
            mostraAllertaErrore("Sei Morto!", "I tuoi punti vita sono scesi a zero. Il gioco è terminato.");
            caricaSchermataMenuPrincipale();
        }
    }

    private boolean isStanzaSenzaNemici(StanzaCombattimento stanzaCombatReale) {
        return stanzaCombatReale == null || stanzaCombatReale.getNemiciStanza().stream().noneMatch(n -> n.getPuntiVita() > 0);
    }

    private void concludiCombattimento() {
        System.out.println("Combattimento Terminato! Vittoria!");
        this.gestoreCombattimento = null;
        this.indiceNemicoTarget = 0;
        salvaESincronizzaTutto();
        aggiornaGrafica();
    }

    private void incrementaTargetNemico() {
        this.indiceNemicoTarget++;
    }

    private void decrementaTargetNemico(List<NemicoDTO> nemici) {
        if (nemici == null || nemici.isEmpty()) return;

        indiceNemicoTarget--;

        if (indiceNemicoTarget < 0) {
            indiceNemicoTarget = nemici.size() - 1;
        }
    }

    // Logica e gestione dei movimenti all'interno della mappa

    private void gestisciSpostamentoEsplorazione(String direzione) {
        StanzaDTO stanza = trovaStanzaCorrente();
        if (stanza == null) return;

        StanzaGenerica stanzaReale = this.statoLogicoBackend.mappaStanze().get(stanza.getIdStanza());
        StanzaGenerica prossimaStanza = switch (direzione) {
            case "Nord" -> stanzaReale.getStanzaNord();
            case "Sud" -> stanzaReale.getStanzaSud();
            case "Est" -> stanzaReale.getStanzaEst();
            case "Ovest" -> stanzaReale.getStanzaOvest();
            default -> null;
        };

        int idProssima = (prossimaStanza != null) ? prossimaStanza.getIdStanza() : -1;
        eseguiSpostamentoGiocatore(idProssima, direzione);
    }

    private void eseguiSpostamentoGiocatore(int idNuovaStanza, String direzioneErrore) {
        if (idNuovaStanza != -1) {
            this.statoLogicoBackend.giocatore().setStanzaCorrente(this.statoLogicoBackend.mappaStanze().get(idNuovaStanza));
            salvaESincronizzaTutto();
            aggiornaGrafica();
        } else {
            mostraAllertaErrore("Muro!", "Non c'è nessuna stanza a " + direzioneErrore + ".");
        }
    }

    // Persistenza dei dati con Salvataggio in file JSON e sincronizzazione

    private void salvaESincronizzaTutto() {
        sincronizzaDTOConBackend();
        new it.unicam.cs.mpgc.rpg125585.backend.convertitori.ConvertitorePartita()
                .salvaPartitaInCorso("salvataggi/salvataggio.json", this.statoLogicoBackend.giocatore(), this.statoLogicoBackend.mappaStanze());
    }

    private void sincronizzaDTOConBackend() {
        this.statoPartita.setGiocatore(new GiocatoreDTO(this.statoLogicoBackend.giocatore()));
        this.statoPartita.setIdStanzaCorrente(this.statoLogicoBackend.giocatore().getStanzaCorrente().getIdStanza());
        this.statoPartita.setMappaStanze(this.statoLogicoBackend.mappaStanze().values().stream().map(StanzaDTO::new).toList());
    }

    // Aggiornamento dei componenti grafici di JavaFX

    private void aggiornaGrafica() {
        if (this.statoPartita == null) return;

        riempiAreaGiocatore();
        StanzaDTO stanzaCorrente = trovaStanzaCorrente();

        if (stanzaCorrente == null) {
            mostraAllertaErrore("Errore di Caricamento", "Stanza corrente non trovata nella mappa.");
            return;
        }

        lblNomeStanza.setText(stanzaCorrente.getNomeStanza());
        lblDescrizioneStanza.setText(stanzaCorrente.getDescrizioneStanza());

        if (stanzaCorrente.isStanzaLoot()) {
            mostraAreaLoot();
            riempiAreaLoot(stanzaCorrente);
        } else {
            nascondiAreaLoot();
        }

        gestioneStanzaCombattimento(stanzaCorrente);
    }

    private void riempiAreaGiocatore() {
        GiocatoreDTO giocatore = this.statoPartita.getGiocatore();
        lblGiocatore.setText("Classe: " + giocatore.getTipoClasse());
        lblVitaGiocatore.setText("Vita: " + giocatore.getPuntiVita() + "/" + giocatore.getVitaMassima());
        lblAttaccoGiocatore.setText("Attacco: " + giocatore.getPuntiAttacco());
        lblScudoGiocatore.setText("Scudo: " + giocatore.getPuntiScudo());
    }

    private void mostraAreaLoot() {
        impostaVisibilitaAreaLoot(true);
        btnPrendiArtefatto.setDisable(false);
    }

    private void nascondiAreaLoot() {
        impostaVisibilitaAreaLoot(false);
    }

    private void impostaVisibilitaAreaLoot(boolean visibile) {
        lblNomeArtefatto.setVisible(visibile);
        lblNomeArtefatto.setManaged(visibile);
        lblDescrizioneArtefatto.setVisible(visibile);
        lblDescrizioneArtefatto.setManaged(visibile);
        btnPrendiArtefatto.setVisible(visibile);
        btnPrendiArtefatto.setManaged(visibile);
    }

    private void riempiAreaLoot(StanzaDTO stanzaCorrente) {
        List<ArtefattoDTO> artefatti = stanzaCorrente.getArtefattiNellaStanza();

        if (artefatti != null && !artefatti.isEmpty()) {
            btnPrendiArtefatto.setDisable(false);
            gestioneNumeroArtefatti(artefatti);
        } else {
            lblNomeArtefatto.setText("Nessun Artefatto qui");
            lblDescrizioneArtefatto.setText("Hai già saccheggiato questa stanza.");
            btnPrendiArtefatto.setDisable(true);
        }
    }

    private void gestioneNumeroArtefatti(List<ArtefattoDTO> artefatti) {
        if (artefatti.size() == 1) {
            ArtefattoDTO artefatto = artefatti.getFirst();
            lblNomeArtefatto.setText(artefatto.getNomeArtefatto());
            lblDescrizioneArtefatto.setText(artefatto.getDescrizioneArtefatto());
        } else {
            StringBuilder nomi = new StringBuilder();
            StringBuilder descrizioni = new StringBuilder();
            for (int i = 0; i < artefatti.size(); i++) {
                nomi.append(artefatti.get(i).getNomeArtefatto()).append(i < artefatti.size() - 1 ? ", " : "");
                descrizioni.append("- ").append(artefatti.get(i).getDescrizioneArtefatto()).append(i < artefatti.size() - 1 ? "\n" : "");
            }
            lblNomeArtefatto.setText(nomi.toString());
            lblDescrizioneArtefatto.setText(descrizioni.toString());
        }
    }

    private void gestioneStanzaCombattimento(StanzaDTO stanzaCorrente) {
        if (stanzaCorrente.isStanzaCombattimento() && haNemiciVivi(stanzaCorrente)) {
            StanzaCombattimento stanzaCombatReale = (StanzaCombattimento)
                    this.statoLogicoBackend.mappaStanze().get(stanzaCorrente.getIdStanza());
            if (this.gestoreCombattimento == null) {
                this.gestoreCombattimento = new GestoreCombattimento(this.statoLogicoBackend.giocatore(),
                        stanzaCombatReale.getNemiciStanza());
            }
            mostraAreaCombattimento();
            riempiAreaCombattimento(stanzaCorrente);
        } else {
            this.gestoreCombattimento = null;
            nascondiAreaNemici();
        }
    }

    private boolean haNemiciVivi(StanzaDTO stanza) {
        if (stanza.getNemiciNellaStanza() == null) return false;

        return stanza.getNemiciNellaStanza().stream().anyMatch(nemico -> nemico.getPuntiVita() > 0);
    }

    private void mostraAreaCombattimento() {
        impostaVisibilitaAreaNemici(true);
        btnStanzaNord.setText("Attacca");
        btnStanzaSud.setDisable(true);
    }

    private void riempiAreaCombattimento(StanzaDTO stanzaCorrente) {
        // Filtriamo SOLO i nemici vivi da mostrare nell'interfaccia a carosello
        List<NemicoDTO> nemiciVivi = stanzaCorrente.getNemiciNellaStanza().stream()
                .filter(nemico -> nemico.getPuntiVita() > 0).toList();

        if (!nemiciVivi.isEmpty()) {
            validaIndiceTarget(nemiciVivi.size());
            int precedente = (indiceNemicoTarget == 0) ? nemiciVivi.size() - 1 : indiceNemicoTarget - 1;
            int successivo = (indiceNemicoTarget + 1) % nemiciVivi.size();

            aggiornaDatiGraficiNemico(nemiciVivi.get(indiceNemicoTarget));
            gestioneSelezioneNemici(nemiciVivi, precedente, successivo);
        }
    }

    private void validaIndiceTarget(int size) {
        if (indiceNemicoTarget >= size) indiceNemicoTarget = 0;

        if (indiceNemicoTarget < 0) indiceNemicoTarget = size - 1;
    }

    private void aggiornaDatiGraficiNemico(NemicoDTO nemico) {
        lblNomeNemico.setText(nemico.getNomeNemico() + " (" + nemico.getTipoNemico() + ")");
        lblVitaNemico.setText("Vita: " + nemico.getPuntiVita() + "/" + nemico.getVitaMassima());
        lblAttaccoNemico.setText("Attacco: " + nemico.getPuntiAttacco());
        lblScudoNemico.setText("Scudo: " + nemico.getPuntiScudo());
    }

    private void gestioneSelezioneNemici(List<NemicoDTO> nemiciVivi, int precedente, int successivo) {
        boolean ciSonoPiuNemici = nemiciVivi.size() > 1;
        btnStanzaOvest.setText("< " + nemiciVivi.get(precedente).getNomeNemico());
        btnStanzaOvest.setDisable(!ciSonoPiuNemici);
        btnStanzaEst.setText(nemiciVivi.get(successivo).getNomeNemico() + " >");
        btnStanzaEst.setDisable(!ciSonoPiuNemici);
    }

    private void nascondiAreaNemici() {
        impostaVisibilitaAreaNemici(false);
        btnStanzaNord.setText("Stanza Nord");
        btnStanzaSud.setText("Stanza Sud");
        btnStanzaOvest.setText("Stanza Ovest");
        btnStanzaEst.setText("Stanza Est");
        btnStanzaNord.setDisable(false);
        btnStanzaSud.setDisable(false);
        btnStanzaOvest.setDisable(false);
        btnStanzaEst.setDisable(false);
    }

    private void impostaVisibilitaAreaNemici(boolean visibile) {
        lblNomeNemico.setVisible(visibile);
        lblNomeNemico.setManaged(visibile);
        lblVitaNemico.setVisible(visibile);
        lblVitaNemico.setManaged(visibile);
        lblScudoNemico.setVisible(visibile);
        lblScudoNemico.setManaged(visibile);
        lblAttaccoNemico.setVisible(visibile);
        lblAttaccoNemico.setManaged(visibile);
    }

    private void mostraAllertaErrore(String errore, String motivo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(errore);
        alert.setContentText(motivo);
        alert.showAndWait();
    }

    private void caricaSchermataMenuPrincipale() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_menu_principale.fxml"));
            Stage stageCorrente = (Stage) btnStanzaNord.getScene().getWindow();
            stageCorrente.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            mostraAllertaErrore("Errore Critico", "Impossibile tornare al menu principale.");
        }
    }
}