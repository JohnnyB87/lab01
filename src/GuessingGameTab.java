import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GuessingGameTab extends Tab{

    public GuessingGameTab(){

        GuessingGame game = new GuessingGame();

        Button exit = new Button();
        Button reset = new Button();
        Button guess = new Button();
        Label label = new Label();
        TextField txtFld = new TextField();

        this.setText("Guessing Game");

        String colour = "#111287";

        exit.setText("Exit");
        reset.setText("Reset");
        guess.setText("Guess");

        txtFld.setLayoutX(75);
        txtFld.setLayoutY(50);
        txtFld.setAlignment(Pos.CENTER);

        label.setText("Guesses Left: " + game.getGuessesLeft());
        label.setLayoutX(10);
        label.setLayoutY(10);
        label.setTextFill(Color.web("#ff0000"));


        TilePane tileButtons = new TilePane(Orientation.VERTICAL);
        tileButtons.setAlignment(Pos.CENTER);
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.setBackground(new Background(new BackgroundFill
                (Color.web(colour),CornerRadii.EMPTY, Insets.EMPTY)));

        guess.setMinWidth(tileButtons.getPrefWidth());
        reset.setMinWidth(tileButtons.getPrefWidth());
        exit.setMinWidth(tileButtons.getPrefWidth());

        tileButtons.getChildren().addAll(label, txtFld, guess, reset, exit);

        exit.setOnAction(e -> game.exitGame());
        reset.setOnAction(e -> game.resetGame(txtFld, label, guess));
        guess.setOnAction(e -> {
            game.run(txtFld.getText(), txtFld, label, guess);
        });

        txtFld.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                game.run(txtFld.getText(), txtFld, label, guess);
            }
        });

        this.setContent(tileButtons);
    }

}
