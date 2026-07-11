package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.backend.convertitori.ConvertitorePartita;
import it.unicam.cs.mpgc.rpg125585.backend.convertitori.StatoGiocoLocale;
import it.unicam.cs.mpgc.rpg125585.dto.GiocatoreDTO;
import it.unicam.cs.mpgc.rpg125585.dto.SalvataggioDTO;
import it.unicam.cs.mpgc.rpg125585.dto.StanzaDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceltaClasseController {
    @FXML private Button btnArciere, btnCavaliere, btnLanciere;
    @FXML private Label lblStatisticheArciere, lblStatisticheCavaliere, lblStatisticheLanciere;

    private final String pathSalvataggio = "salvataggi/salvataggio.json";
    private final ConvertitorePartita convertitorePartita = new ConvertitorePartita();

    @FXML
    private void initialize() {
        lblStatisticheArciere.setText("Arciere - Vita: 90 | Attacco: 30 | Scudo: 2");
        lblStatisticheCavaliere.setText("Cavaliere - Vita: 100 | Attacco: 20 | Scudo: 3");
        lblStatisticheLanciere.setText("Lanciere - Vita: 95 | Attacco: 25 | Scudo: 2");
    }

    @FXML
    public void handleSceltaClasse(ActionEvent event) {
        Button bottonePremuto = (Button) event.getSource();
        String classeScelta = determinaClasse(bottonePremuto.getId());
        avviaNuovaPartita(classeScelta, bottonePremuto);
    }

    private SalvataggioDTO creaSalvataggioDTO(StatoGiocoLocale stato) {
        GiocatoreDTO giocatoreDTO = new GiocatoreDTO(stato.giocatore());
        List<StanzaDTO> stanzeDTO = stato.mappaStanze().values().stream()
                .map(StanzaDTO::new)
                .toList();
        int idStanzaIniziale = stato.giocatore().getStanzaCorrente().getIdStanza();
        return new SalvataggioDTO(giocatoreDTO, stanzeDTO, idStanzaIniziale);
    }

    private void cambiaSchermataGioco(Button bottone, SalvataggioDTO salvataggioDTO, StatoGiocoLocale stato)
    throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_gioco.fxml"));
        Parent root = loader.load();
        GiocoController giocoController = loader.getController();
        giocoController.inizializzaInterfaccia(salvataggioDTO, stato);
        Stage stageCorrente = (Stage) bottone.getScene().getWindow();
        stageCorrente.setScene(new Scene(root));
    }

    private void avviaNuovaPartita(String classeScelta, Button bottoneSorgente) {
        try {
            StatoGiocoLocale statoReale = convertitorePartita.inizializzaNuovaPartita(classeScelta, pathSalvataggio);
            SalvataggioDTO nuovaPartitaDTO = creaSalvataggioDTO(statoReale);
            cambiaSchermataGioco(bottoneSorgente, nuovaPartitaDTO, statoReale);
        }catch (IOException e){
            mostraFinestraErroreGrave("Impossibile avviare la partita", e.getMessage());
        }
    }

    private void mostraFinestraErroreGrave(String titolo, String messaggio) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    private String determinaClasse(String idBottone) {
        return switch (idBottone) {
            case "btnArciere" -> "Arciere";
            case "btnCavaliere" -> "Cavaliere";
            case "btnLanciere" -> "Lanciere";
            default -> throw new IllegalArgumentException("Bottone non riconosciuto: " + idBottone);
        };
    }

}