package it.unicam.cs.mpgc.rpg125585.gui;

import it.unicam.cs.mpgc.rpg125585.backend.utils.GestoreFile;
import it.unicam.cs.mpgc.rpg125585.dto.SalvataggioDTO;
import it.unicam.cs.mpgc.rpg125585.dto.GiocatoreDTO;
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
    @FXML
    private Button btnArciere;
    @FXML
    private Button btnCavaliere;
    @FXML
    private Button btnLanciere;
    @FXML
    private Label lblStatisticheArciere;
    @FXML
    private Label lblStatisticheCavaliere;
    @FXML
    private Label lblStatisticheLanciere;

    private final GestoreFile gestoreFile = new GestoreFile();

    @FXML
    public void handleSceltaClasse(ActionEvent event) {
        Button bottonePremuto = (Button) event.getSource();
        GiocatoreDTO nuovoGiocatore = null;
        if(bottonePremuto == btnArciere){
            System.out.println("Configuro partita per: Arciere");
            nuovoGiocatore =
                    new GiocatoreDTO("Arciere", 50, 50, 20,1);
        } else if(bottonePremuto == btnCavaliere){
            System.out.println("Configuro partita per: Cavaliere");
            nuovoGiocatore =
                    new GiocatoreDTO("Cavaliere", 70, 70, 10,3);
        } else if(bottonePremuto == btnLanciere){
            System.out.println("Configuro partita per: Lanciere");
            nuovoGiocatore =
                    new GiocatoreDTO("Lanciere", 60, 60, 15,2);
        }
        List<StanzaDTO> stanzeMappa = gestoreFile.caricaMappaBase("mappa_base.json");
        int idStanzaIniziale = 0;
        SalvataggioDTO nuovaPartita = new SalvataggioDTO(nuovoGiocatore, stanzeMappa, idStanzaIniziale);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/schermata_gioco.fxml"));
            Parent root = loader.load();
            GiocoController giocoController = loader.getController();
            giocoController.inizializzaInterfaccia(nuovaPartita);
            Stage stageCorrente = (Stage) bottonePremuto.getScene().getWindow();
            Scene scene = new Scene(root);
        } catch (IOException e) {
            System.out.println("Errore nel caricamento di schermata_gioco.fxml");
            e.printStackTrace();
        }
    }
}