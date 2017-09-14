package Dominos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.List;

public class GameGUI
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


    public HBox dominoHandView(Player currentPlayer)
    {

        DominoHand dominoHand = currentPlayer.dominoHand;
        List<Domino> dominoList = dominoHand.dominoList;
        Button buttonFlipDomino = new Button("");
        buttonFlipDomino.setPadding(Insets.EMPTY);
        HBox hboxDomino = new HBox(40);
        buttonFlipDomino.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE)
                {
                    if(currentImage != null) flipDomino(currentImage, selectedDomino);
                    System.out.println("(" + selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                }
                else if (event.getCode() == KeyCode.D)
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
                else if (event.getCode() == KeyCode.A)
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
        ImageView[] dominoImage = new ImageView[dominoList.size()];
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
                public void handle(MouseEvent event)
                {
                    selectedDomino = mapImageDomino.get(selectedImage);
                    System.out.println("(" +selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                    if(currentImage != null) unhighlight(currentImage);
                    currentImage = selectedImage;
                    System.out.println(selectedDomino);
                    highlight(currentImage);

                    if (event.getButton() == MouseButton.SECONDARY)
                    {
                        if(currentImage != null) flipDomino(currentImage, selectedDomino);
                        System.out.println("(" + selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                    }

                }
            });
            hboxDomino.getChildren().add(dominoImage[i]);



        }

        hboxDomino.setAlignment(Pos.BOTTOM_CENTER);
        hboxDomino.getChildren().add(buttonFlipDomino);
        return hboxDomino;


    }
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

