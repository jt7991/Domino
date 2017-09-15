package Dominos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class GameCoordinator
{
  public static final int HIGHEST_DOMINO_DIGIT = 6;
  public static final int STARTING_HAND_SIZE = 9;
  public static final int NUMBER_OF_PLAYERS = 2;
  public static final int NUMBER_OF_DOMINOS = ((HIGHEST_DOMINO_DIGIT + 1) * (HIGHEST_DOMINO_DIGIT + 2)) / 2;
  public static Boolean winner = false;
  public static int currentTurn = 0;
  public static Player[] playerArray = new Player[NUMBER_OF_PLAYERS];
  public static Domino[] orderedDominoSet = new Domino[NUMBER_OF_DOMINOS];

  public static void initializeOrderedDominos()
  {
    int k = 0;
    for (int i = 0; i <= HIGHEST_DOMINO_DIGIT; i++)
    {
      for (int j = 0; j <= i; j++)
      {
        orderedDominoSet[k] = new Domino();
        orderedDominoSet[k].digitA = j;
        orderedDominoSet[k].digitB = i;
        orderedDominoSet[k].imagePath = "Dominos/DominoImages/Domino_" + j + "-" + i + ".png";
        k++;
      }
    }
  }

  public static Scene startGame()
  {
    initializeOrderedDominos();
    Boneyard.initializeBoneyard(orderedDominoSet);

    for (int i = 0; i < NUMBER_OF_PLAYERS; i++)
    {
      playerArray[i] = new Player();
      playerArray[i].initialDrawDominos(STARTING_HAND_SIZE);
    }

    for (int i = 0; i < NUMBER_OF_PLAYERS; i++)
    {
      System.out.println("\nPlayer[" + i + "]");
      playerArray[i].printHand();
    }

    Boneyard.debugBoneyard();
    GameGUI gameGUI = new GameGUI();
    return gameGUI.showGame(playerArray);


  }

}