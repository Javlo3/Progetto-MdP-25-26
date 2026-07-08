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
    @FXML private Button btnArciere;
    @FXML private Button btnCavaliere;
    @FXML private Button btnLanciere;
    @FXML private Label lblStatisticheArciere;
    @FXML private Label lblStatisticheCavaliere;
    @FXML private Label lblStatisticheLanciere;

    private final String pathSalvataggio = "salvataggi/salvataggio.json";
    private final ConvertitorePartita convertitorePartita = new ConvertitorePartita();

    @FXML
    public void handleSceltaClasse(ActionEvent event) {
        Button bottonePremuto = (Button) event.getSource();
        String classeScelta = "";

        if (bottonePremuto == btnArciere) {
            classeScelta = "Arciere";
        } else if (bottonePremuto == btnCavaliere) {
            classeScelta = "Cavaliere";
        } else if (bottonePremuto == btnLanciere) {
            classeScelta = "Lanciere";
        }

        System.out.println("Configuro partita logica per: " + classeScelta);

        try {
            // 1. Inizializziamo il dominio logico e creiamo il primo auto-salvataggio tramite il convertitore dedicato
            StatoGiocoLocale statoReale = convertitorePartita.inizializzaNuovaPartita(classeScelta, pathSalvataggio);

            // 2. Proiettiamo lo stato reale appena generato in una struttura DTO per la visualizzazione immediata della GUI
            GiocatoreDTO giocatoreDTO = new GiocatoreDTO(statoReale.giocatore());
            List<StanzaDTO> stanzeDTO = statoReale.mappaStanze().values().stream()
                    .map(StanzaDTO::new)
                    .toList();
            int idStanzaIniziale = statoReale.giocatore().getStanzaCorrente().getIdStanza();

            SalvataggioDTO nuovaPartitaDTO = new SalvataggioDTO(giocatoreDTO, stanzeDTO, idStanzaIniziale);

            // 3. Eseguiamo il passaggio controllato alla schermata di gioco principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_gioco.fxml"));
            Parent root = loader.load();

            GiocoController giocoController = loader.getController();
            // Passiamo la coppia strutturata (DTO + Entity)
            giocoController.inizializzaInterfaccia(nuovaPartitaDTO, statoReale);

            Stage stageCorrente = (Stage) bottonePremuto.getScene().getWindow();
            stageCorrente.setScene(new Scene(root));

        } catch (IOException e) {
            System.err.println("Errore nel caricamento di schermata_gioco.fxml o inizializzazione della partita");
            e.printStackTrace();
        }
    }
}