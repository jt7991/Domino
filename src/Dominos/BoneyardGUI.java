package Dominos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BoneyardGUI
{
    public Button viewBoneyard(Player currentPlayer)
    {
        int totalBoneyardPieces = Boneyard.boneyardDominos.size();
        Button buttonBoneyard = new Button("Boneyard: " + totalBoneyardPieces);
        buttonBoneyard.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent e)
          {

              int numBoneyardPieces = currentPlayer.drawFromBoneyard();
              buttonBoneyard.setText("Boneyard: " + numBoneyardPieces);
          }

        });
        return buttonBoneyard;
    }
}
