package it.unicam.cs.mpgc.rpg125585.dto;

import java.util.List;

public class SalvataggioDTO {
    // Stato attuale del Giocatore
    public String classeGiocatore; // "Arciere", "Cavaliere", "Lanciere"
    public String nomeGiocatore;
    public int hpCorrenti;
    public int hpMassimi;
    public int attaccoCorrente;
    public int scudoCorrente;
    public int idStanzaCorrente; // Per sapere dove ricollocare il PG al caricamento

    // Stato del Mondo (La lista delle stanze aggiornate con i nemici rimasti vivi)
    public List<StanzaDTO> statoStanze;
}