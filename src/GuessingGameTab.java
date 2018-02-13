import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class GuessingGameTab extends GameTab implements GameRules{

    private TextField txtFld;
    private Label label;
    private int guessesLeft = 6;
    private int num = generateNumber();
    private Alert alert;

    public GuessingGameTab(String title, String buttonName){
        super(title, buttonName);
//        StackPane pane = new StackPane();
        Pane pane = new Pane();

        this.txtFld = new TextField();
        this.txtFld.setLayoutX(125);
        this.txtFld.setLayoutY(100);
        this.txtFld.setMaxWidth(150);
        this.txtFld.setMinWidth(150);
        this.txtFld.setAlignment(Pos.CENTER);

        this.label = new Label(String.format("Guesses Left: %d", this.guessesLeft));
        this.label.setLayoutX(160);
        this.label.setLayoutY(80);

        pane.getChildren().addAll(txtFld, label);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#ff0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        super.addPane(pane);

        super.getExit().setOnAction(e -> exitGame());
        super.getReset().setOnAction(e -> resetGame());
        super.getGuess().setOnAction(e -> guessButtonPressed());

        this.txtFld.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                guessButtonPressed();
            }
        });
    }

    public void guessButtonPressed(){
        run(this.txtFld.getText());
        this.txtFld.clear();
        this.txtFld.requestFocus();
        this.label.setText(String.format("Guesses Left: ", this.guessesLeft));
    }

    @Override
    public void run(String s) {
        System.out.println("s : " + s);

        boolean isInt = isNumber(s);
        if(isInt) {
            int guess = Integer.parseInt(s);
            checkResult(guess);

            if (this.guessesLeft == 0) {
                this.txtFld.setDisable(true);
                super.getGuess().setDisable(true);
                loser();
            }

            txtFld.clear();
            txtFld.requestFocus();
            label.setText(String.format("Guesses Left: %d", this.guessesLeft));

            System.out.println("Number: " + this.num);
            System.out.println("Lives Left: " + this.guessesLeft);
        }
    }

    @Override
    public void checkResult(int guess) {
        boolean winner = this.num == guess;
        if(winner) {
            winner();
        }
        else {
            higherOrLower(guess);
            this.guessesLeft--;
        }
    }

    @Override
    public void resetGame(Node... nodes) {
        this.alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to reset the game.\n All current progress will be lost?"
                ,ButtonType.YES,ButtonType.NO);
        alert.showAndWait();

        if(this.alert.getResult() == ButtonType.YES) {
            this.guessesLeft = 6;
            this.label.setText("Guesses Left: " + Integer.toString(guessesLeft));
            this.txtFld.clear();
            this.txtFld.requestFocus();
            this.txtFld.setDisable(false);
            this.num = generateNumber();
        }
    }

    @Override
    public void exitGame() {
        this.alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to quit?",ButtonType.YES,ButtonType.NO);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        else {
            alert.close();
        }
    }

    @Override
    public void winner() {
        int num = this.guessesLeft > 4 ? 4 : this.guessesLeft;
        this.alert = new Alert(Alert.AlertType.INFORMATION,
                "CONGRATULATIONS\nYou Win a"+ num + " star prize.",ButtonType.OK);
        alert.showAndWait();
    }

    @Override
    public void loser() {
        this.alert = new Alert(Alert.AlertType.INFORMATION,"YOU LOSE",ButtonType.OK);
        alert.showAndWait();
    }

    @Override
    public void prizes() {

    }

    public void higherOrLower(int guess) {

        String s = "You Guessed too ";
        this.alert = new Alert(Alert.AlertType.INFORMATION, s, ButtonType.OK);

        s = guess > this.num ? s + "high." : s + "low.";

        this.alert.setContentText(s);
        this.alert.showAndWait();
    }

    public boolean isNumber(String guess) {
        try {
            Integer.parseInt(guess);
            return true;

        }catch(NumberFormatException nfe){

            this.alert = new Alert(Alert.AlertType.ERROR
                    ,"Enter a valid number.", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
    }

    public int generateNumber(){
        return new Random().nextInt(100)+1;
    }
}
