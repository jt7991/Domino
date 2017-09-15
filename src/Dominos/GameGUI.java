package Dominos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

public class GameGUI
{
    public static Domino selectedDomino;
    public static ImageView currentImage;
    HashMap<ImageView, Domino> mapImageDomino = new HashMap<>();
    HashMap<Domino, ImageView> mapDominoImage = new HashMap<>();
    List<Domino> gameBoard;
    Player currentPlayer;
    Player[] playerArray = new Player[2];
    int dominoX =700;
    int rightDominoY = 350;
    int leftDominoY = 350;
    Domino firstDominoPlayed;
    static int leftDisplacementY = 0;
    static int rightDisplacementY = 0;
    static int leftDominoX = 700;
    static int rightDominoX = 670;
    Pane pane = new Pane();
    VBox vbox;
    HBox hbox = new HBox(40);
    private void winner(int player)
    {
        Label labelWinner = new Label("Player " + (player + 1) + " won!!");
        labelWinner.setLayoutX(700);
        labelWinner.setLayoutY(500);
        labelWinner.setStyle("-fx-background-color: white");
        labelWinner.setFont(Font.font(60));
        pane.getChildren().add(labelWinner);
    }
    private void checkWinner(Player[] playerArray)
    {
        for(int i =0; i<2; i++)
        {
           if(playerArray[i].dominoHand.dominoList.size() == 0) winner(i);
        }
    }
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

    public  void drawGameBoard()
    {
        pane.setPrefSize(1400, 725);
        pane.setStyle("-fx-background-color: black");



    }
    public void addDominoToBoard(Domino playedDomino, int x, int y)
    {
        ImageView dominoImage = mapDominoImage.get(playedDomino);
        if(dominoImage == null)
        {
            dominoImage= new ImageView(playedDomino.imagePath);
            dominoImage.setRotate(270);
            dominoImage.setFitWidth(60);
            dominoImage.setFitHeight(60);
            dominoImage.setPreserveRatio(true);
            System.out.print("Flipped: " + playedDomino.flipped);
            if (playedDomino.digitA> playedDomino.digitB)dominoImage.setRotate(dominoImage.getRotate() + 180);
        }
        dominoImage.setLayoutX(x);
        dominoImage.setLayoutY(y);
        dominoImage.setOnMouseClicked((MouseEvent event)-> {});
        unhighlight(dominoImage);
        selectedDomino = null;
        pane.getChildren().add(dominoImage);

    }
    public void addDominoToHand(Domino domino)
    {
        ImageView dominoImage = new ImageView();
        dominoImage.setImage(new Image(domino.imagePath));
        dominoImage.setFitWidth(60);
        dominoImage.setFitHeight(60);
        dominoImage.setRotate(270);
        ImageView selectedImage = dominoImage;
        dominoImage.setPreserveRatio(true);
        mapImageDomino.put(selectedImage, domino);
        mapDominoImage.put(domino, selectedImage);
        dominoImage.setOnMouseClicked(new EventHandler<MouseEvent>(){
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
        hbox.getChildren().add(dominoImage);
    }
    public void dominoHandView(Player currentPlayer)
    {

        DominoHand dominoHand = currentPlayer.dominoHand;
        List<Domino> dominoList = dominoHand.dominoList;
        Button buttonFlipDomino = new Button("");
        buttonFlipDomino.setPadding(Insets.EMPTY);
        buttonFlipDomino.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event) {
                if(selectedDomino != null)
                {
                if (event.getCode() == KeyCode.SPACE)
                {
                    if(currentImage != null) flipDomino(currentImage, selectedDomino);
                    System.out.println("(" + selectedDomino.digitA + "," + selectedDomino.digitB + ") " );
                }
                else if (event.getCode() == KeyCode.D)
                {
                    rightPlaceDomino(currentPlayer);
                    for (int i = 0; i < dominoHand.dominoList.size(); i++)
                    {
                        System.out.print("(" + dominoHand.dominoList.get(i).digitA + "," + dominoHand.dominoList.get(i).digitB + ") " );
                    }
                    System.out.println();
                }
                else if (event.getCode() == KeyCode.A)
                {
                    leftPlaceDomino(currentPlayer);
                }
                }
            }
        });
        hbox.getChildren().add(buttonFlipDomino);
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
            mapDominoImage.put(dominoList.get(i), selectedImage);
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
            hbox.getChildren().add(dominoImage[i]);



        }

        hbox.setAlignment(Pos.BOTTOM_CENTER);



    }
    public void leftPlaceDomino(Player player)
    {
        System.out.println("LEFT");
        if(player.placeDomino(selectedDomino, 'L'))
        {
            leftDominoX = leftDominoX - 30;
            leftDominoY = leftDominoY + leftDisplacementY;
            if(leftDisplacementY == 0)
            {
                leftDisplacementY = -31;
                rightDisplacementY = 31;
            }
            leftDisplacementY = leftDisplacementY * (-1);
            hbox.getChildren().remove(currentImage);
            addDominoToBoard(selectedDomino, leftDominoX, leftDominoY);
            checkWinner(playerArray);
            if (currentPlayer == playerArray[1]) currentPlayer = playerArray[0];
            else
            {

                currentPlayer = playerArray[1];
                Domino computerDomino = currentPlayer.computerPlayTurn();
                selectedDomino = computerDomino;
                if (computerDomino.direction == 'L')
                {
                    leftPlaceDomino(currentPlayer);
                }
                else
                {
                    rightPlaceDomino(currentPlayer);
                }

            }
        }
    }
    public void rightPlaceDomino(Player player)
    {
        System.out.println("RIGHT");

        if(player.placeDomino(selectedDomino, 'R'))
        {
            rightDominoX = rightDominoX + 30;
            rightDominoY = rightDominoY + rightDisplacementY;
            if (rightDisplacementY == 0)
            {
                leftDisplacementY = 31;
                rightDisplacementY = -31;
            }
            rightDisplacementY = rightDisplacementY * (-1);
            hbox.getChildren().remove(currentImage);
            addDominoToBoard(selectedDomino, rightDominoX, rightDominoY);
            checkWinner(playerArray);
            if (currentPlayer == playerArray[1]) currentPlayer = playerArray[0];
            else
            {
                currentPlayer = playerArray[1];
                Domino computerDomino = currentPlayer.computerPlayTurn();
                selectedDomino = computerDomino;
                if (computerDomino.direction == 'L')
                {
                    leftPlaceDomino(currentPlayer);
                }
                else
                {
                    rightPlaceDomino(currentPlayer);
                }
            }
        }
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
                addDominoToHand(currentPlayer.dominoHand.dominoList.get(currentPlayer.dominoHand.dominoList.size() -1));
                buttonBoneyard.setText("Boneyard: " + numBoneyardPieces);
            }

        });
        return buttonBoneyard;
    }
    public Scene showGame(Player[] playerList)
    {
        playerArray = playerList;
        drawGameBoard();
        currentPlayer = playerList[0];
        vbox = new VBox();
        dominoHandView(playerList[0]);
        HBox hboxFull = new HBox(30);
        hboxFull.getChildren().addAll(hbox, viewBoneyard(playerList[0]));
        vbox.getChildren().addAll(pane, hboxFull);
        Scene scene = new Scene(vbox, 1400,800);
        return scene;
    }

}

