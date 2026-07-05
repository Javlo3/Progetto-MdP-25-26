package it.unicam.cs.mpgc.rpg125585.backEnd.convertitori;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.StanzaGenerica;
import java.util.Map;

public record StatoGiocoLocale(Giocatore giocatore, Map<Integer, StanzaGenerica> mappaStanze) {
}