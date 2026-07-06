package it.unicam.cs.mpgc.rpg125585.backend.gestionecombattimenti;

import it.unicam.cs.mpgc.rpg125585.backend.entita.giocatore.Giocatore;
import it.unicam.cs.mpgc.rpg125585.backend.entita.nemici.Nemico;
import java.util.List;

public class GestoreCombattimento {
    private final Giocatore giocatore;
    private final List<Nemico> nemiciNellaStanza;

    // Il bersaglio corrente selezionato dal giocatore tramite la GUI
    private Nemico nemicoAgganciato;

    public GestoreCombattimento(Giocatore giocatore, List<Nemico> nemiciNellaStanza) {
        this.giocatore = giocatore;
        this.nemiciNellaStanza = nemiciNellaStanza;

        // Di default, all'inizio agganciamo il primo nemico vivo della lista
        selezionaProssimoBersaglioAutomatico();
    }

    /**
     * Permette alla GUI (tasti cambio bersaglio) di cambiare il nemico attivo.
     * Il cambio avviene solo se il nemico è presente nella stanza ed è vivo.
     */
    public void cambiaBersaglio(Nemico nuovoNemico) {
        if (nuovoNemico != null && nemiciNellaStanza.contains(nuovoNemico) && nuovoNemico.getPuntiVita() > 0) {
            this.nemicoAgganciato = nuovoNemico;
        }
    }

    /**
     * Gestisce l'attacco del giocatore contro il nemico attualmente agganciato.
     * Se il nemico sopravvive, risponde al fuoco.
     * @return true se l'azione è andata a buon fine, false se non c'era un bersaglio valido.
     */
    public boolean eseguiTurnoAttacco() {
        if (nemicoAgganciato == null || nemicoAgganciato.getPuntiVita() <= 0) {
            return false; // Nessun bersaglio valido da colpire
        }

        // 1. Il giocatore colpisce il bersaglio agganciato
        nemicoAgganciato.dannoRicevuto(giocatore.getPuntiAttacco());

        // 2. Se il nemico è morto, aggiorniamo il target. Altrimenti, contrattacca!
        if (nemicoAgganciato.getPuntiVita() <= 0) {
            selezionaProssimoBersaglioAutomatico();
        } else {
            // Solo il nemico agganciato risponde al fuoco
            giocatore.dannoRicevuto(nemicoAgganciato.getPuntiAttacco());
        }

        return true;
    }

    /**
     * Cerca il primo nemico ancora in vita nella stanza e lo aggancia.
     * Se sono tutti morti, imposta il target a null (combattimento terminato).
     */
    private void selezionaProssimoBersaglioAutomatico() {
        for (Nemico n : nemiciNellaStanza) {
            if (n.getPuntiVita() > 0) {
                this.nemicoAgganciato = n;
                return;
            }
        }
        this.nemicoAgganciato = null;
    }

    /**
     * Controlla se ci sono ancora nemici vivi nella stanza.
     * Utile alla classe principale per sapere quando chiudere la schermata di scontro.
     */
    public boolean ciSonoNemiciVivi() {
        return this.nemicoAgganciato != null;
    }

    public boolean giocatoreMorto() {
        return this.giocatore.getPuntiVita() <= 0;
    }

    // Getter utili per la GUI per sapere chi evidenziare a schermo e la lista totale
    public Nemico getNemicoAgganciato() {
        return nemicoAgganciato;
    }

    public List<Nemico> getNemiciNellaStanza() {
        return nemiciNellaStanza;
    }
}