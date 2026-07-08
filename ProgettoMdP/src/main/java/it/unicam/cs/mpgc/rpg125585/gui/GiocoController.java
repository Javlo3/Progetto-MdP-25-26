package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.dto.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class GiocoController {
    @FXML
    private Label lblNomeStanza;
    @FXML
    private Label lblDescrizioneStanza;
    @FXML
    private Label lblNomeArtefatto;
    @FXML
    private Label lblDescrizioneArtefatto;

    @FXML
    private Label lblGiocatore;
    @FXML
    private Label lblVitaGiocatore;
    @FXML
    private Label lblScudoGiocatore;
    @FXML
    private Label lblAttaccoGiocatore;

    @FXML
    private Label lblNomeNemico;
    @FXML
    private Label lblVitaNemico;
    @FXML
    private Label lblScudoNemico;
    @FXML
    private Label lblAttaccoNemico;

    @FXML
    private Button btnStanzaNord;
    @FXML
    private Button btnStanzaSud;
    @FXML
    private Button btnStanzaOvest;
    @FXML
    private Button btnStanzaEst;

    @FXML
    private Button btnPrendiArtefatto;

    private SalvataggioDTO statoPartita;

    public void inizializzaInterfaccia(SalvataggioDTO partita) {
        this.statoPartita = partita;
        aggiornaGrafica();
    }

    private void aggiornaGrafica() {
        if(this.statoPartita == null) return;
        GiocatoreDTO giocatore = this.statoPartita.getGiocatore();
        lblGiocatore.setText("Classe: " +giocatore.getTipoClasse());
        lblVitaGiocatore.setText("Vita: " + giocatore.getPuntiVita() + "/" + giocatore.getVitaMassima());
        lblAttaccoGiocatore.setText("Attacco: " + giocatore.getPuntiAttacco());
        lblScudoGiocatore.setText("Scudo: " + giocatore.getPuntiScudo());

        StanzaDTO stanzaCorrente = trovaStanzaCorrente();
        if(stanzaCorrente == null) {
            mostraAllertaErrore("Errore di Caricamento", "Stanza corrente non trovata nella mappa.");
            return;
        }
        lblNomeStanza.setText(stanzaCorrente.getNomeStanza());
        lblDescrizioneStanza.setText(stanzaCorrente.getDescrizioneStanza());

        if(stanzaCorrente.isStanzaLoot()) {
            List<ArtefattoDTO> artefatti = stanzaCorrente.getArtefattiNellaStanza();
            if(artefatti != null  && !artefatti.isEmpty()) {
                btnPrendiArtefatto.setDisable(false);
                if(artefatti.size() == 1) {
                    ArtefattoDTO artefatto = artefatti.get(0);
                    lblNomeArtefatto.setText(artefatto.getNomeArtefatto());
                    lblDescrizioneArtefatto.setText(artefatto.getDescrizioneArtefatto());
                } else {
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
            }  else {
                }
        }
    }

    private StanzaDTO trovaStanzaCorrente() {}
}