package Dominos;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Domino
{
    int digitA, digitB;
    int orientation;
}

public class Boneyard
{
    public static List<Domino> boneyardDominos = new ArrayList<Domino>();
    public static Domino[] orderedDominoSet = new Domino[Rules.NUMBER_OF_DOMINOS];
    public static Player[] playerArray = new Player[Rules.NUMBER_OF_PLAYERS];

    public static void initializeBoneyard()
    {
        int k = 0;
        for (int i = 0; i <= 6; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                orderedDominoSet[k] = new Domino();

                orderedDominoSet[k].digitA = i;
                orderedDominoSet[k].digitB = j;
                boneyardDominos.add(orderedDominoSet[k]);
                k++;
            }
        }


        Collections.shuffle(boneyardDominos);

        for(int i =0; i< Rules.NUMBER_OF_DOMINOS; i++)
        {
            System.out.println(boneyardDominos.get(i).digitA);
        }

    }
    public static void dealDominos()
    {
        for(int j = 0; j< Rules.NUMBER_OF_PLAYERS; j++)
        {

            for (int i = 0; i < Rules.STARTING_HAND_SIZE; i++)
            {
                System.out.print("Dominos:" + (boneyardDominos.get((Rules.STARTING_HAND_SIZE*j + i))).digitA); //+ "  i:" + playerArray[j].handDominos);
                playerArray[j].handDominos.add(boneyardDominos.get((Rules.STARTING_HAND_SIZE*j + i)));
            }
        }
    }
    public static void debugPrintHands()
    {
        for(int j = 0; j< Rules.NUMBER_OF_PLAYERS; j++)
        {
            System.out.println("\nPlayer["+ j + "]");
            for (int i = 0; i < Rules.STARTING_HAND_SIZE; i++)
            {
                //System.out.print("(" + Boneyard.playerArray[j].handDominos.get(i).digitA + "," + Boneyard.playerArray[j].handDominos.get(i).digitB + ") " );
            }
        }
    }
}