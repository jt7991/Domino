package Dominos;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    public DominoHand dominoHand;
    public void initialDrawDominos(int numberOfDominos)
    {
        List<Domino> tempDominos = Boneyard.drawDominos(numberOfDominos);
        dominoHand = new DominoHand(tempDominos);
    }
    public void printHand()
    {
        for (int i = 0; i < dominoHand.dominoList.size(); i++)
        {
            System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );
        }
    }
    public Boolean placeDomino( Domino domino, Character direction)
    {
        System.out.println("\nDomino: (" + domino.digitA + "," + domino.digitB + ") ");
        Boolean legalPlay = GameBoard.checkLegalPlay(domino, direction);
        if(legalPlay)
        {
            dominoHand.removeDomino(domino);
            for (int i = 0; i < dominoHand.dominoList.size(); i++)
            {
                System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );

            }
            System.out.println();
            GameBoard.playPiece(domino,direction);
            return true;

        }
        else return false;
    }
    public int drawFromBoneyard()
    {
        dominoHand.dominosToHand(Boneyard.drawDominos(1));
        return Boneyard.boneyardDominos.size();
    }

    public Domino computerPlayTurn()
    {
        Domino domino = null;
        while (true)
        {
            domino = dominoHand.findDomino();
            if (domino == null)
            {
                drawFromBoneyard();
            }
            else return domino;
        }
    }
}
