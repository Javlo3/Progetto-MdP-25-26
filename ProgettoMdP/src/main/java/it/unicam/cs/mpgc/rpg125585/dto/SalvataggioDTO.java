package it.unicam.cs.mpgc.rpg125585.dto;

import java.util.List;

public class SalvataggioDTO {

    private GiocatoreDTO giocatore;
    private List<StanzaDTO> mappaStanze;

    public SalvataggioDTO() {
    }

    public SalvataggioDTO(GiocatoreDTO giocatore, List<StanzaDTO> mappaStanze) {
        this.giocatore = giocatore;
        this.mappaStanze = mappaStanze;
    }

    public GiocatoreDTO getGiocatore() {
        return giocatore;
    }

    public List<StanzaDTO> getMappaStanze() {
        return mappaStanze;
    }
}