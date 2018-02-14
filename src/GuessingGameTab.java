import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class GuessingGameTab extends GameTab implements GameRules{

    private Pane pane = new Pane();
    private TextField txtFld;
    private Label label;
    private ArrayList<Label> records = new ArrayList<>();
    private int recordPositionY = 110;

    public boolean isWinner() {
        return winner;
    }

    private boolean winner = false;
    private int guessesLeft = 6;
    private int num = generateNumber();
    private Alert alert;

    public GuessingGameTab(String title, String buttonName){
        super(title, buttonName);
//        StackPane pane = new StackPane();


        this.txtFld = new TextField();
        this.txtFld.setLayoutX(125);
        this.txtFld.setLayoutY(100);
        this.txtFld.setMaxWidth(150);
        this.txtFld.setMinWidth(150);
        this.txtFld.setAlignment(Pos.CENTER);

        this.label = new Label(String.format("Guesses Left: "));
        this.label.setLayoutX(160);
        this.label.setLayoutY(80);

        this.pane.getChildren().addAll(txtFld, label);
        this.pane.setBackground(new Background(new BackgroundFill(Color.web("#ff0000"), CornerRadii.EMPTY, Insets.EMPTY)));
        super.addPane(this.pane);

        super.getExitButton().setOnAction(e -> exitGame());
        super.getResetButton().setOnAction(e -> resetGame());
        super.getGuessButton().setOnAction(e -> guessButtonPressed());

        this.txtFld.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                guessButtonPressed();
            }
        });
    }

    public TextField getTxtFld() {
        return txtFld;
    }

    public Label getLabel() {
        return label;
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
                super.getGuessButton().setDisable(true);
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
        this.winner = this.num == guess;
        if(this.winner) {
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
            this.getGuessButton().setDisable(false);
            for(Label l : this.records) {
                l.setText("");
                this.pane.getChildren().remove(l);
            }
            this.records.clear();

            this.recordPositionY = 110;
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
        this.alert = new Alert(Alert.AlertType.INFORMATION,
                "CONGRATULATIONS\nYou Win a 4 star prize.",ButtonType.OK);
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

        int recordPositionX = 160;
        int size = this.records.size();
        String s = "You Guessed too ";
        this.alert = new Alert(Alert.AlertType.INFORMATION, s, ButtonType.OK);

        String higOrLow = guess > this.num ? "high." : "low.";

        this.records.add(new Label(String.format("Too %s: %d", higOrLow, guess)));
        this.records.get(size).setLayoutY(this.recordPositionY += 10);
        this.records.get(size).setLayoutX(recordPositionX);
        this.records.get(size).setPadding(new Insets(15,15,15,15));
        this.pane.getChildren().add(this.records.get(size));

        System.out.println(this.pane.getChildren());

        this.alert.setContentText(s + higOrLow);
        this.alert.showAndWait();
    }

    public boolean isNumber(String guess) {
        String s = "";
        try {
            int n = Integer.parseInt(guess);
            if(n > 0 && n < 101)
                return true;
            else
                s = "Enter a number between 1 & 100 inclusive";

        }catch(NumberFormatException nfe){
            s = "Enter a number please.";
        }
        this.alert = new Alert(Alert.AlertType.ERROR
                ,s, ButtonType.OK);
        alert.showAndWait();
        return false;
    }

    public int generateNumber(){
        return new Random().nextInt(100)+1;
    }
}
