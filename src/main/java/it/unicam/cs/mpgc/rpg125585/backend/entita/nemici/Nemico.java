package it.unicam.cs.mpgc.rpg125585.backend.entita.nemici;

import it.unicam.cs.mpgc.rpg125585.backend.entita.EntitaGenerale;

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