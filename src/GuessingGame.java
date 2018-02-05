import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import  javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GuessingGame {

    private static int windowWidth = 300;
    private static int windowLength = 250;

    public static VBox guessingGameTab(Button b1, Button b2, Button b3) {

        VBox vbox = new VBox();



        Tab guessingGame = new Tab();
        guessingGame.setText("Guessing Game");
        guessingGame.setContent(new Rectangle(windowWidth, windowLength, Color.LIGHTBLUE));



        b1 = new Button("Exit");
        b2 = new Button("Reset");
        b3 = new Button("Guess");

        b1.setLayoutX(200);
        b1.setLayoutY(200);

        vbox.getChildren().addAll(b1,b2,b3);



        return vbox;
    }
}
