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
