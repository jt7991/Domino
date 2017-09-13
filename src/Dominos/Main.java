package Dominos;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class Main extends Application
{
    public Stage stage;
    public Scene welcomeScreen()
    {
        Label labelTitle = new Label("Dominos");
        labelTitle.setFont(Font.font(60));
        Button buttonStart = new Button("Start Game");
        VBox vboxWelcome = new VBox();
        vboxWelcome.setAlignment(CENTER);
        vboxWelcome.setSpacing(10);
        vboxWelcome.getChildren().addAll(labelTitle, buttonStart);
        Scene scene = new Scene(vboxWelcome, 1200,800);
        buttonStart.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                stage.setScene(GameCoordinator.startGame());
            }
        });
        return scene;

    }
    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage)
    {
        this.stage = stage;
        stage.setTitle("Dominos");
        stage.show();
        stage.setScene(welcomeScreen());

    }
}
