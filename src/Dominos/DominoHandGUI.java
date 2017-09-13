package Dominos;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class DominoHandGUI
{
    public static Domino selectedDomino;
    public static ImageView currentImage;
    private static void unhighlight(ImageView image)
    {
        image.setStyle("");
    }
    private static void highlight(ImageView image)
    {
        image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,0,0,1), 20, 0, 0, 0)");
    }
    public static HBox dominoHandView(DominoHand dominoHand)
    {
        List<Domino> dominoList = dominoHand.dominoList;
        HBox hboxDomino = new HBox(40);
        ImageView[] dominoImage = new ImageView[dominoList.size()];


        for (int i = 0; i < dominoList.size(); i++)
        {
            final int index = i;
            dominoImage[i] = new ImageView();
            Image image =new Image (dominoList.get(i).imagePath);
            dominoImage[i].setImage(image);
            dominoImage[i].setFitWidth(60);
            dominoImage[i].setFitHeight(60);
            dominoImage[i].setRotate(90);
            dominoImage[i].setPreserveRatio(true);
            dominoImage[i].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("(" + dominoHand.dominoList.get(index).digitA + "," + dominoHand.dominoList.get(index).digitB + ") " );
                    if(currentImage != null) unhighlight(currentImage);
                    currentImage = dominoImage[index];
                    selectedDomino = dominoHand.dominoList.get(index);
                    System.out.println(selectedDomino);
                    highlight(currentImage);
                }
            });

            hboxDomino.getChildren().add(dominoImage[i]);


        }
        hboxDomino.setAlignment(Pos.BOTTOM_CENTER);
                


        return hboxDomino;

    }
}
