package it.unicam.cs.mpgc.rpg125585.backend.convertitori;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backend.mappa.stanze.StanzaGenerica;
import java.util.Map;

public record StatoGiocoLocale(Giocatore giocatore, Map<Integer, StanzaGenerica> mappaStanze) {
}