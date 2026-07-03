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

    // Dentro la classe Nemico (o una classe di utilità nei nemici)
    public static Nemico creaDaDTO(NemicoDTO dto) {
        return switch (dto.getTipoNemico()) {
            case "GoblinSemplice" ->
                    new GoblinSemplice(dto.getNomeNemico(), dto.getVitaMassima(), dto.getPuntiVita(),
                            dto.getPuntiAttacco(), dto.getPuntiScudo());
            case "GoblinLanciere" ->
                    new GoblinLanciere(dto.getNomeNemico(), dto.getVitaMassima(), dto.getPuntiVita(),
                            dto.getPuntiAttacco(), dto.getPuntiScudo());
            case "Stregone" -> new Stregone(dto.getNomeNemico(), dto.getVitaMassima(), dto.getPuntiVita(),
                    dto.getPuntiAttacco(), dto.getPuntiScudo());
            default -> throw new IllegalArgumentException("Tipo nemico sconosciuto");
        };
    }
}