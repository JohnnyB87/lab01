import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameTab extends Tab{

    private BorderPane pane = new BorderPane();
    private Button exit = new Button();
    private Button reset = new Button();
    private Button guess = new Button();

    public GameTab(String title, String buttonName) {


        this.setText(title);

        String colour = "#111287";

        exit.setText("Exit");
        reset.setText("Reset");
        guess.setText(buttonName);

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setBackground(new Background(new BackgroundFill(Color.BLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        tileButtons.setAlignment(Pos.CENTER);
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.setMinHeight(75);
        //tileButtons.setBackground(new Background(new BackgroundFill
                //(Color.web(colour),CornerRadii.EMPTY, Insets.EMPTY)));

        int buttonWidth = 60;

        this.guess.setMinWidth(buttonWidth);
        this.guess.setMaxWidth(buttonWidth);
        this.exit.setMinWidth(buttonWidth);
        this.exit.setMaxWidth(buttonWidth);
        this.reset.setMinWidth(buttonWidth);
        this.reset.setMaxWidth(buttonWidth);


        tileButtons.getChildren().addAll(guess, reset, exit);


       // pane.getChildren().add(tileButtons);
        this.pane.setBottom(tileButtons);
        this.setContent(this.pane);


//        exit.setOnAction(e -> game.exitGame());
//        reset.setOnAction(e -> game.resetGame(txtFld, label, guess));
//        guess.setOnAction(e -> {
//            game.run(txtFld.getText(), txtFld, label, guess);
//        });

    }

    public void addPane(Node node){
        this.pane.setCenter(node);
    }

    public Button getExit() {
        return exit;
    }

    public Button getReset() {
        return reset;
    }

    public Button getGuess() {
        return guess;
    }


}
