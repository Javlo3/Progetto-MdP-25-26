package it.unicam.cs.mpgc.rpg125585.dto;

import it.unicam.cs.mpgc.rpg125585.backEnd.entita.nemici.Nemico;

public class NemicoDTO {

    private String tipoNemico;
    private String nomeNemico;
    private int vitaMassima;
    private int puntiVita;
    private int puntiAttacco;
    private int puntiScudo;

    public NemicoDTO() {
    }

    public NemicoDTO(String tipoNemico, String nomeNemico, int vitaMassima, int puntiVita, int puntiAttacco, int puntiScudo) {
        this.tipoNemico = tipoNemico;
        this.nomeNemico = nomeNemico;
        this.vitaMassima = vitaMassima;
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
        this.puntiScudo = puntiScudo;
    }

    public NemicoDTO(Nemico nemico) {
        this.tipoNemico = nemico.getClass().getSimpleName();
        this.nomeNemico = nemico.getNomeNemico();
        this.vitaMassima = nemico.getVitaMassima();
        this.puntiVita = nemico.getPuntiVita();
        this.puntiAttacco = nemico.getPuntiAttacco();
        this.puntiScudo = nemico.getPuntiScudo();
    }

    public String getTipoNemico() {
        return tipoNemico;
    }

    public String getNomeNemico() {
        return nomeNemico;
    }

    public int getVitaMassima() {
        return vitaMassima;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiAttacco() {
        return puntiAttacco;
    }

    public int getPuntiScudo() {
        return puntiScudo;
    }
}