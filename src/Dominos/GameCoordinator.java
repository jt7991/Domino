package Dominos;

import java.util.ArrayList;
import java.util.List;

class Rules
{
    public static final int HIGHEST_DOMINO_DIGIT = 6;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int NUMBER_OF_DOMINOS = ((HIGHEST_DOMINO_DIGIT + 1)*(HIGHEST_DOMINO_DIGIT+2))/2;
}

class Player
{
    List<Domino> handDominos = new ArrayList<Domino>();

}
public class GameCoordinator
{
    public static void startGame()
    {
        Boneyard.initializeBoneyard();
        Boneyard.dealDominos();
        Boneyard.debugPrintHands();
    }

}