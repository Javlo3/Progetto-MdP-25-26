package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.backend.convertitori.ConvertitorePartita;
import it.unicam.cs.mpgc.rpg125585.backend.convertitori.StatoGiocoLocale;
import it.unicam.cs.mpgc.rpg125585.backend.utils.GestoreFile;
import it.unicam.cs.mpgc.rpg125585.dto.SalvataggioDTO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuPrincipaleController {
    private final String pathSalvataggio = "salvataggi/salvataggio.json";
    private final GestoreFile gestoreFile = new GestoreFile();
    private final ConvertitorePartita convertitorePartita = new ConvertitorePartita();

    // Handler FXML, gestione dei click sui pulsanti della GUI

    @FXML
    public void handleCaricaPartita(ActionEvent event) {
        if (!gestoreFile.esisteSalvataggio(pathSalvataggio)) {
            mostraAllertaErrore("Nessun salvataggio trovato", "Non ci sono partite salvate. " +
                    "Inizia una nuova avventura!");
        } else {
            try {
                // 1. Ricostruiamo lo stato logico reale del backend (Entità) dal file esterno
                StatoGiocoLocale statoReale = convertitorePartita.caricaPartitaEsistente(pathSalvataggio);
                // 2. Leggiamo il DTO leggero per riempire l'interfaccia grafica
                SalvataggioDTO partitaCaricata = gestoreFile.caricaPartitaSalvata(pathSalvataggio);
                if (partitaCaricata != null && statoReale != null) {
                    cambiaSchermataGioco(event, partitaCaricata, statoReale);
                } else {
                    mostraAllertaErrore("Errore di Caricamento", "Il file di salvataggio è" +
                            " corrotto o illeggibile.");
                }
            } catch (Exception e) {
                mostraAllertaErrore("Errore di Caricamento", "Impossibile ricostruire" +
                        " lo stato della partita.");
            }
        }
    }

    @FXML
    public void handleNuovaPartita(ActionEvent event) {
        if (gestoreFile.esisteSalvataggio(pathSalvataggio)) {
            Alert alertConferma = new Alert(Alert.AlertType.CONFIRMATION);
            alertConferma.setTitle("Attenzione: Salvataggio già esistente");
            alertConferma.setHeaderText("C'è già una partita salvata");
            alertConferma.setContentText("Se ne inizi una nuova, quest'ultima sovrascriverà il salvataggio precedente." +
                    " Sei sicuro di voler continuare?");
            Optional<ButtonType> risultato = alertConferma.showAndWait();
            if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
                cambiaSchermata(event);
            }
        } else {
            cambiaSchermata(event);
        }
    }

    // Gestione del cambio della schermata verso le pagine successive

    private void cambiaSchermataGioco(ActionEvent event, SalvataggioDTO partita, StatoGiocoLocale statoReale) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_gioco.fxml"));
        try {
            Parent root = loader.load();
            GiocoController giocoController = loader.getController();
            // Passiamo sia il DTO che lo stato logico reale delle Entity
            giocoController.inizializzaInterfaccia(partita, statoReale);
            Stage stageCorrente = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stageCorrente.setScene(new Scene(root));
        } catch (IOException e) {
            mostraAllertaErrore("Errore Critico", "Impossibile passare alla schermata di gioco");
        }
    }

    private void cambiaSchermata(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_scelta_classe.fxml"));
        try {
            Parent root = loader.load();
            Stage stageCorrente = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageCorrente.setScene(new Scene(root));
        } catch (IOException e) {
            mostraAllertaErrore("Errore Critico", "Impossibile caricare la schermata di selezione " +
                    "della classe.");
        }
    }

    private void mostraAllertaErrore(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(titolo);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}

