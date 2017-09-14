package Dominos;

import java.util.ArrayList;
import java.util.List;

public class GameBoard
{
    public List<Domino> gameBoard = new ArrayList<>();
    public  Boolean checkLegalPlay(Domino domino, Character direction)
    {
        if (gameBoard.size() == 0) return true;
        else if (direction == 'R'&& ((gameBoard.get(gameBoard.size() - 1).digitB == domino.digitA) ||
                (gameBoard.get(gameBoard.size() - 1).digitB == 0) ||
                (domino.digitA == 0))) return true;

        else if (direction == 'L' && ((gameBoard.get(0).digitA == domino.digitB) ||
                (gameBoard.get(0).digitA == 0) ||
                (domino.digitB == 0))) return true;

        else return false;
    }
    private List<Domino>
}
