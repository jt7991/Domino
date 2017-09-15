package Dominos;

import java.util.ArrayList;
import java.util.List;

public class DominoHand
{
  public List<Domino> dominoList;

  public DominoHand(List<Domino> dominos)
  {
    dominoList = new ArrayList<Domino>();
    dominoList.addAll(dominos);
  }

  public void removeDomino(Domino domino)
  {
    dominoList.remove(domino);

  }

  public void flipThisDomino(Domino domino)
  {
    int swap;
    swap = domino.digitA;
    domino.digitA = domino.digitB;
    domino.digitB = swap;
    if (domino.flipped == 1) domino.flipped = 0;
    else domino.flipped = 0;


  }

  public Domino findDomino()
  {
    Domino legalDomino;
    for (int i = 0; i < dominoList.size(); i++)
    {
      if (GameBoard.checkLegalPlay(dominoList.get(i), 'L'))
      {
        legalDomino = dominoList.get(i);
        legalDomino.direction = 'L';
        return legalDomino;
      } else if (GameBoard.checkLegalPlay(dominoList.get(i), 'R'))
      {
        legalDomino = dominoList.get(i);
        legalDomino.direction = 'R';
        return legalDomino;
      }
      flipThisDomino(dominoList.get(i));
      if (GameBoard.checkLegalPlay(dominoList.get(i), 'L'))
      {
        legalDomino = dominoList.get(i);
        legalDomino.direction = 'L';
        return legalDomino;
      } else if (GameBoard.checkLegalPlay(dominoList.get(i), 'R'))
      {
        legalDomino = dominoList.get(i);
        legalDomino.direction = 'R';
        return legalDomino;
      }
    }
    return null;
  }

  public void dominosToHand(List<Domino> dominos)
  {
    dominoList.addAll(dominos);
  }

  public static void flipDomino(Domino domino)
  {
    int swap = domino.digitA;
    domino.digitA = domino.digitB;
    domino.digitB = swap;
  }
}
