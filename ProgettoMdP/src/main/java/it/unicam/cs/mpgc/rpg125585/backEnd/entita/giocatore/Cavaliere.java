package it.unicam.cs.mpgc.rpg125585.backEnd.entita.giocatore;

public class Cavaliere extends Giocatore{

    public Cavaliere(){
        super(70, 15, 3, 1);
    }
    public Cavaliere(int puntiVitaSalvataggio, int puntiAttaccoSalvataggio, int puntiScudoSalvataggio,
                     int distanzaAttaccoSalvataggio) {
        super(puntiVitaSalvataggio, puntiAttaccoSalvataggio, puntiScudoSalvataggio, distanzaAttaccoSalvataggio);
    }
}