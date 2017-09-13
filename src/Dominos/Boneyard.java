package Dominos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Boneyard
{
    public static List<Domino> boneyardDominos = new ArrayList<Domino>();

    public static void initializeBoneyard(Domino[] orderedDominos)
    {
        List<Domino> tempDominos = new ArrayList<>(Arrays.asList(orderedDominos));
        boneyardDominos = tempDominos;
        Collections.shuffle(boneyardDominos);

    }
    public static List<Domino> drawDominos(int numberOfDominos)
    {
        List<Domino> dominosDrawn = new ArrayList<Domino>();
        for (int i = 0; i < numberOfDominos; i++)
        {
            dominosDrawn.add(boneyardDominos.get(i));
            System.out.println(boneyardDominos.get(i).digitA);
            boneyardDominos.remove(i);
        }
        return dominosDrawn;
    }
//   TO BE MOVED:
//
//    public static void debugPrintHands()
//    {
//        for(int j = 0; j< Rules.NUMBER_OF_PLAYERS; j++)
//        {
//            System.out.println("\nPlayer["+ j + "]");
//            for (int i = 0; i < Rules.STARTING_HAND_SIZE; i++)
//            {
//                System.out.print("(" + Boneyard.playerArray[j].handDominos.get(i).digitA + "," + Boneyard.playerArray[j].handDominos.get(i).digitB + ") " );
//            }
//        }
//    }
    public static void debugBoneyard()
    {
        System.out.println("\nBoneyard");
        for (int i = 0; i < boneyardDominos.size(); i++)
        {
            System.out.print("(" + boneyardDominos.get(i).digitA + "," + boneyardDominos.get(i).digitB + ") " );
        }
    }
}