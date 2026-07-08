package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.backend.convertitori.StatoGiocoLocale;
import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.Nemico;
import it.unicam.cs.mpgc.rpg125585.backend.gestionecombattimenti.GestoreCombattimento;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaCombattimento;
import it.unicam.cs.mpgc.rpg125585.dto.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class GiocoController {
    @FXML private Label lblNomeStanza;
    @FXML private Label lblDescrizioneStanza;
    @FXML private Label lblNomeArtefatto;
    @FXML private Label lblDescrizioneArtefatto;

    @FXML private Label lblGiocatore;
    @FXML private Label lblVitaGiocatore;
    @FXML private Label lblScudoGiocatore;
    @FXML private Label lblAttaccoGiocatore;

    @FXML private Label lblNomeNemico;
    @FXML private Label lblVitaNemico;
    @FXML private Label lblScudoNemico;
    @FXML private Label lblAttaccoNemico;

    @FXML private Button btnStanzaNord;
    @FXML private Button btnStanzaSud;
    @FXML private Button btnStanzaOvest;
    @FXML private Button btnStanzaEst;
    @FXML private Button btnPrendiArtefatto;

    private SalvataggioDTO statoPartita;
    private int indiceNemicoTarget;
    private GestoreCombattimento gestoreCombattimento;
    private StatoGiocoLocale statoLogicoBackend;

    public void inizializzaInterfaccia(SalvataggioDTO partita, StatoGiocoLocale statoReale) {
        this.statoPartita = partita;
        this.statoLogicoBackend = statoReale;
        this.indiceNemicoTarget = 0;
        aggiornaGrafica();
    }

    private void aggiornaGrafica() {
        if(this.statoPartita == null) return;
        riempiAreaGiocatore();
        StanzaDTO stanzaCorrente = trovaStanzaCorrente();
        if(stanzaCorrente == null) {
            mostraAllertaErrore("Errore di Caricamento", "Stanza corrente non trovata nella mappa.");
            return;
        }
        lblNomeStanza.setText(stanzaCorrente.getNomeStanza());
        lblDescrizioneStanza.setText(stanzaCorrente.getDescrizioneStanza());
        if(stanzaCorrente.isStanzaLoot()) {
            gestisciStatoLoot(stanzaCorrente);
        } else {
            nascondiAreaLoot();
        }
        if (stanzaCorrente.isStanzaCombattimento()) {
            // Se entri in una nuova stanza o il gestore è null, lo crei passandogli i dati veri dal backend
            if (this.gestoreCombattimento == null) {
                Giocatore giocatoreReale = this.statoLogicoBackend.giocatore();
                StanzaCombattimento stanzaCombatReale = (StanzaCombattimento) this.statoLogicoBackend.mappaStanze()
                        .get(stanzaCorrente.getIdStanza());
                // NOTA: Qui dovrai passare il giocatore vero e la lista nemici dal tuo modello/DTO
                List<Nemico> nemiciReali = stanzaCombatReale.getNemiciStanza();
                this.gestoreCombattimento = new GestoreCombattimento(giocatoreReale, nemiciReali);
            }
            mostraAreaCombattimento();
            riempiAreaCombattimento(stanzaCorrente);
        } else {
            this.gestoreCombattimento = null; // Resetti quando esci dal combattimento
            nascondiAreaNemici();
        }
    }

    private void riempiAreaGiocatore() {
        GiocatoreDTO giocatore = this.statoPartita.getGiocatore();
        lblGiocatore.setText("Classe: " +giocatore.getTipoClasse());
        lblVitaGiocatore.setText("Vita: " + giocatore.getPuntiVita() + "/" + giocatore.getVitaMassima());
        lblAttaccoGiocatore.setText("Attacco: " + giocatore.getPuntiAttacco());
        lblScudoGiocatore.setText("Scudo: " + giocatore.getPuntiScudo());
    }

    private void gestisciStatoLoot(StanzaDTO stanzaCorrente) {
        mostraAreaLoot();
        riempiAreaLoot(stanzaCorrente);
    }

    private void mostraAreaLoot() {
        lblNomeArtefatto.setVisible(true);
        lblNomeArtefatto.setManaged(true);
        lblDescrizioneArtefatto.setVisible(true);
        lblDescrizioneArtefatto.setManaged(true);
    }

    private void riempiAreaLoot(StanzaDTO stanzaCorrente) {
        List<ArtefattoDTO> artefatti = stanzaCorrente.getArtefattiNellaStanza();
        if(artefatti != null  && !artefatti.isEmpty()) {
            btnPrendiArtefatto.setDisable(false);
            if(artefatti.size() == 1) {
                ArtefattoDTO artefatto = artefatti.get(0);
                lblNomeArtefatto.setText(artefatto.getNomeArtefatto());
                lblDescrizioneArtefatto.setText(artefatto.getDescrizioneArtefatto());
            } else {
                assemblaEImpostaTestiLootMultipli(artefatti);
            }
        } else{
            lblNomeArtefatto.setText("Nessun Artefatto qui");
            lblDescrizioneArtefatto.setText("Hai già saccheggiato questa stanza.");
            btnPrendiArtefatto.setDisable(true);
        }

    }

    private void assemblaEImpostaTestiLootMultipli(List<ArtefattoDTO> artefatti) {
        StringBuilder nomiArtefattiAssemblati = new StringBuilder();
        StringBuilder descrizioneArtefattiAssembli = new StringBuilder();
        for(int i = 0; i < artefatti.size(); i++) {
            ArtefattoDTO artefatto = artefatti.get(i);
            nomiArtefattiAssemblati.append(artefatto.getNomeArtefatto());
            descrizioneArtefattiAssembli.append("- ").append(artefatto.getDescrizioneArtefatto());
            if(i < artefatti.size()-1) {
                nomiArtefattiAssemblati.append(", ");
                descrizioneArtefattiAssembli.append("\n");
            }
        }
        lblNomeArtefatto.setText(nomiArtefattiAssemblati.toString());
        lblDescrizioneArtefatto.setText(descrizioneArtefattiAssembli.toString());
    }

    private void nascondiAreaLoot() {
        lblNomeArtefatto.setVisible(false);
        lblNomeArtefatto.setManaged(false);
        lblDescrizioneArtefatto.setVisible(false);
        lblDescrizioneArtefatto.setManaged(false);
    }

    private void gestisciStatoCombattimento(StanzaDTO stanzaCorrente) {
        mostraAreaCombattimento();
        riempiAreaCombattimento(stanzaCorrente);
    }

    private void mostraAreaCombattimento() {
        lblNomeNemico.setVisible(true);
        lblNomeNemico.setManaged(true);
        lblVitaNemico.setVisible(true);
        lblVitaNemico.setManaged(true);
        lblScudoNemico.setVisible(true);
        lblScudoNemico.setManaged(true);
        lblAttaccoNemico.setVisible(true);
        lblAttaccoNemico.setManaged(true);
        btnStanzaNord.setText("Attacca");
        btnStanzaSud.setDisable(true);
        btnStanzaOvest.setText("< Nemico Precedente");
        btnStanzaEst.setText("Nemico Successivo >");
    }

    private void riempiAreaCombattimento(StanzaDTO stanzaCorrente) {
        List<NemicoDTO> nemici = stanzaCorrente.getNemiciNellaStanza();
        if (nemici != null && !nemici.isEmpty()) {
            if (indiceNemicoTarget >= nemici.size()) indiceNemicoTarget = 0;

            NemicoDTO nemico = nemici.get(indiceNemicoTarget);
            lblNomeNemico.setText(nemico.getTipoNemico());
            lblVitaNemico.setText("Vita: " + nemico.getPuntiVita() + "/" + nemico.getVitaMassima());
            lblAttaccoNemico.setText("Attacco: " + nemico.getPuntiAttacco());
            lblScudoNemico.setText("Scudo: " + nemico.getPuntiScudo());

            boolean ciSonoPiuNemici = nemici.size() > 1;
            btnStanzaEst.setDisable(!ciSonoPiuNemici);
            btnStanzaOvest.setDisable(!ciSonoPiuNemici);
        } else {
            lblNomeNemico.setText("Nemici Sconfitti!");
            lblVitaNemico.setText("");
            lblAttaccoNemico.setText("");
            lblScudoNemico.setText("");
            btnStanzaNord.setDisable(true);
        }
    }

    private void nascondiAreaNemici() {
        lblNomeNemico.setVisible(false);
        lblNomeNemico.setManaged(false);
        lblVitaNemico.setVisible(false);
        lblVitaNemico.setManaged(false);
        lblScudoNemico.setVisible(false);
        lblScudoNemico.setManaged(false);
        lblAttaccoNemico.setVisible(false);
        lblAttaccoNemico.setManaged(false);
    }

    private void mostraAllertaErrore(String errore, String motivo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(errore);
        alert.setContentText(motivo);
        alert.showAndWait();
    }

    private StanzaDTO trovaStanzaCorrente() {
        int idCercato = statoPartita.getIdStanzaCorrente();
        for (StanzaDTO stanza : statoPartita.getMappaStanze()) {
            if (stanza.getIdStanza() == idCercato) {
                return stanza;
            }
        }
        return null;
    }

    @FXML
    public void handleStanzaNord(ActionEvent event) {
        StanzaDTO stanza = trovaStanzaCorrente();
        if (stanza != null && stanza.isStanzaCombattimento()) {
            // 1. Diciamo al gestore di calcolare il turno di attacco
            boolean attaccoEseguito = gestoreCombattimento.eseguiTurnoAttacco();
            if (attaccoEseguito) {
                // 2. Se il combattimento è finito (tutti morti), il backend imposterà il target a null
                if (!gestoreCombattimento.ciSonoNemiciVivi()) {
                    System.out.println("Combattimento Terminato! Vittoria!");
                }
                // 3. Rinfreschi la GUI (le label con la vita calata si aggiornano da sole!)
                aggiornaGrafica();
            }
        } else {
            // Logica di movimento standard verso Nord
        }
    }
}