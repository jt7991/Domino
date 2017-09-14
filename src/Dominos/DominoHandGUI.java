package Dominos;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.List;

public class DominoHandGUI
{
    public static Domino selectedDomino;
    public static ImageView currentImage;
    HashMap<ImageView, Domino> mapImageDomino = new HashMap<>();


    private static void flipDomino(ImageView image, Domino domino)
    {
        image.setRotate(image.getRotate() + 180);
        DominoHand.flipDomino(domino);
    }
    private static void unhighlight(ImageView image)
    {
        image.setStyle("");
    }

    private static void highlight(ImageView image)
    {
        image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,0,0,1), 20, 0, 0, 0)");
    }

    public HBox initializeDominoImages(Player currentPlayer)
    {
        List<Domino> dominoList = currentPlayer.dominoHand.dominoList;
        ImageView[] dominoImage = new ImageView[dominoList.size()];
        HBox hboxDomino = new HBox(40);
        for (int i = 0; i < dominoList.size(); i++)
        {
            dominoImage[i] = new ImageView();
            Image image =new Image (dominoList.get(i).imagePath);
            dominoImage[i].setImage(image);
            dominoImage[i].setFitWidth(60);
            dominoImage[i].setFitHeight(60);
            dominoImage[i].setRotate(270);
            ImageView selectedImage = dominoImage[i];
            dominoImage[i].setPreserveRatio(true);
            mapImageDomino.put(selectedImage, dominoList.get(i));
            dominoImage[i].setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    selectedDomino = mapImageDomino.get(selectedImage);
                    System.out.println("(" +selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                    if(currentImage != null) unhighlight(currentImage);
                    currentImage = selectedImage;
                    System.out.println(selectedDomino);
                    highlight(currentImage);
                }
            });
            hboxDomino.getChildren().add(dominoImage[i]);



        }

        hboxDomino.setAlignment(Pos.BOTTOM_CENTER);
        return hboxDomino;

    }

    public HBox dominoHandView(Player currentPlayer)
    {

        DominoHand dominoHand = currentPlayer.dominoHand;
        List<Domino> dominoList = dominoHand.dominoList;


        Button buttonFlipDomino = new Button("");
        buttonFlipDomino.setPadding(Insets.EMPTY);
        HBox hboxDomino = initializeDominoImages(currentPlayer);
        buttonFlipDomino.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE)
                {
                    if(currentImage != null) flipDomino(currentImage, selectedDomino);
                    System.out.println("(" + selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                }
                else if (event.getCode() == KeyCode.RIGHT)
                {
                    System.out.println("RIGHT");
                    currentPlayer.placeDomino(selectedDomino, 'R');
                    hboxDomino.getChildren().remove(currentImage);
                    for (int i = 0; i < dominoHand.dominoList.size(); i++)
                    {
                        System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );
                    }
                    System.out.println();
                }
                else if (event.getCode() == KeyCode.LEFT)
                {
                    System.out.println("RIGHT");
                    currentPlayer.placeDomino(selectedDomino, 'L');
                    hboxDomino.getChildren().remove(currentImage);
                    for (int i = 0; i < dominoHand.dominoList.size(); i++)
                    {
                        System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );
                    }
                    System.out.println();
                }
            }
        });
        hboxDomino.getChildren().add(buttonFlipDomino);



        return hboxDomino;

    }
}
