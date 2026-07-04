package it.unicam.cs.mpgc.rpg125585.backEnd.convertitori;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backEnd.mappa.stanze.StanzaGenerica;
import java.util.List;

public record StatoGiocoLocale(Giocatore giocatore, List<StanzaGenerica> mappaStanze) {
}
