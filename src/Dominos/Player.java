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
    public void placeDomino( Domino domino, Character direction)
    {
        Boolean legalPlay = GameBoard.checkLegalPlay(domino, direction);
        if(legalPlay)
        {
            dominoHand.removeDomino(domino);
            for (int i = 0; i < dominoHand.dominoList.size(); i++)
            {
                System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );

            }
            System.out.println();
            //GameBoard.playPiece(domino,direction);

        }
    }
    public int drawFromBoneyard()
    {
        dominoHand.dominosToHand(Boneyard.drawDominos(1));
        return Boneyard.boneyardDominos.size();
    }
    public Boolean playTurn()
    {
        if (dominoHand.dominoList.size() == 0) return true;
        else return false;
    }
}
