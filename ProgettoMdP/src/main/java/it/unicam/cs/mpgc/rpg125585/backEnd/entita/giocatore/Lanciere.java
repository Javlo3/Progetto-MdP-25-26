package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;


public class Lanciere extends Giocatore{

    public Lanciere(){
        super(60, 15, 3, 2);
    }
    public Lanciere(int puntiVitaSalvataggio, int puntiAttaccoSalvataggio, int puntiScudoSalvataggio,
                    int distanzaAttaccoSalvataggio) {
        super(puntiVitaSalvataggio, puntiAttaccoSalvataggio, puntiScudoSalvataggio, distanzaAttaccoSalvataggio);
    }
}