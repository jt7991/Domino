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

  public static void dominosToHand(List<Domino> dominos, DominoHand dominoHand)
  {
      dominoHand.dominoList.addAll(dominos);
  }
}
