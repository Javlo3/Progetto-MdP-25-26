package it.unicam.cs.mpgc.rpg125585.dto;

import java.util.List;

public class StanzaDTO {
    public int idStanza;
    public String tipo;
    public String nomeStanza;
    public String descrizioneStanza;

    // Collegamenti cardinali (Bussola)
    public int idNord;
    public int idSud;
    public int idEst;
    public int idOvest;

    // Contenuti opzionali (Se non presenti nel JSON, Gson li lascerà null)
    public List<NemicoDTO> nemiciContenuti;
    public ArtefattoDTO Artefatto; // La 'A' maiuscola rispecchia la chiave del tuo JSON
}