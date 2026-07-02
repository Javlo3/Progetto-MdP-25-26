package it.unicam.cs.mpgc.rpg125585.dto;

public class ArtefattoDTO {
    public String tipoArtefatto;
    public String nomeArtefatto;
    public String descrizioneArtefatto;
    public int puntiCura;  // Sarà 0 se l'artefatto è un'arma
    public int dannoArma;  // Sarà 0 se l'artefatto è una cura
}