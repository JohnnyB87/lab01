import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class GuessingGame {

    private static TextField txtFld;

    private static Button exit;
    private static Button reset;
    private static Button guess;

    private static int numberOfGuesses = 6;
    private static boolean winner;

    public static Tab guessingGameTab(int windowWidth, int windowLength) {

        Tab tab = new Tab();

        tab.setText("Guessing Game");
        tab.setContent(new Rectangle(windowWidth, windowLength, null));

        String colour = "#111287";

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.web(colour),
                CornerRadii.EMPTY, Insets.EMPTY)));

        txtFld = new TextField();

        exit = new Button("Exit");
        reset = new Button("Reset");
        guess = new Button("Guess");

        txtFld.setLayoutX(75);
        txtFld.setLayoutY(50);
        txtFld.setAlignment(Pos.CENTER);


        exit.setOnAction(e -> System.out.println("EXITED MOFO"));


        TilePane tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setAlignment(Pos.CENTER);
        //tileButtons.setPadding(new Insets(50, 70, 0, 70));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.setBackground(new Background(new BackgroundFill
                (Color.web(colour),CornerRadii.EMPTY, Insets.EMPTY)));

        guess.setMinWidth(tileButtons.getPrefWidth());
        reset.setMinWidth(tileButtons.getPrefWidth());
        exit.setMinWidth(tileButtons.getPrefWidth());

        tileButtons.getChildren().addAll(txtFld, guess, reset, exit);



       // pane.getChildren().addAll(guess, exit, reset);
        tab.setContent(tileButtons);




        return tab;
    }

    public static void run(){
        int num = generateNumber();
        winner = false;

        try {
            while(numberOfGuesses > 0 && !winner) {
                guess.setOnAction(e -> Integer.parseInt(txtFld.getText()));


                System.out.println(winner);

            }
        }
        catch(NumberFormatException nfe){
            System.out.println("Enter a number please.");
        }

    }

    public static boolean checkGuess(int num, int guess){
        return num == guess;
    }



    public static int generateNumber(){
        return new Random().nextInt(100)+1;
    }
}
