package it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.EntitaGenerale;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.goblin.GoblinLanciere;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.goblin.GoblinSemplice;
import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.stregoni.Stregone;
import it.unicam.cs.mpgc.rpg125585.dto.NemicoDTO;

public abstract class Nemico extends EntitaGenerale{
    private final String nomeNemico;

    public Nemico(String nomeNemico, int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        super(vitaMassima, puntiVita, puntiAttacco, puntiScudo);
        this.nomeNemico = nomeNemico;
    }

    public String getNomeNemico() {
        return nomeNemico;
    }
}